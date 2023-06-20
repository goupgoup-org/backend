package com.goupgoup_backend.user.service;

import com.goupgoup_backend.user.config.OAuth2UserInfo;
import com.goupgoup_backend.user.config.OAuth2UserInfoFactory;
import com.goupgoup_backend.user.config.UserPrincipal;
import com.goupgoup_backend.user.domain.User;
import com.goupgoup_backend.user.domain.UserRole;
import com.goupgoup_backend.user.dto.AuthProvider;
import com.goupgoup_backend.user.dto.OAuth2Attribute;
import com.goupgoup_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate
                = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuth2Attribute attributes = OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes.getAttributes(),
                attributes.getAttributeKey()
        );
    }

    private User saveOrUpdate(OAuth2Attribute attribute) {
        User user = userRepository.findByEmail(attribute.getEmail())
                .map(entity -> entity.update(attribute.getName()))
                .orElse(attribute.toEntity());

        return userRepository.save(user);
    }
}