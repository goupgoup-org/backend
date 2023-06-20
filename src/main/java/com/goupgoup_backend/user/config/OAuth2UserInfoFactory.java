package com.goupgoup_backend.user.config;

import com.goupgoup_backend.user.dto.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
        switch (authProvider) {
            case GOOGLE:
                return new GoogleOAuth2User(attributes);
            case KAKAO:
                return new KakaoOAuth2User(attributes);
            default:
                throw new RuntimeException("Unsupported OAuth2 provider");
        }
    }
}
