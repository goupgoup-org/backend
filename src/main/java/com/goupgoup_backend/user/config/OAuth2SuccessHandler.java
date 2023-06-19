package com.goupgoup_backend.user.config;

import com.goupgoup_backend.common.config.JwtTokenProvider;
import com.goupgoup_backend.common.repository.CookieAuthorizationRequestRepository;
import com.goupgoup_backend.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.util.WebUtils.getCookie;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${oauth.authorized-redirect-uri}")
    private String redirectUri;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if(response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = Optional.ofNullable(getCookie(request, "redirect_uri"))
                .map(String::valueOf);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("Unauthorized Redirect URI");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        String token = jwtTokenProvider.createAccessToken(authentication.getName(), authentication.getAuthorities());

        return UriComponentsBuilder.fromUriString("http://localhost:3000/auth/Kakao")
                .queryParam("token", token)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedURI = URI.create(redirectUri);

        if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizedURI.getPort() == clientRedirectUri.getPort()) {
            return true;
        }

        return false;
    }
}
