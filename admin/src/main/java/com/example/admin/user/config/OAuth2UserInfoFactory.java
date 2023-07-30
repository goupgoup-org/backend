package com.example.admin.user.config;


import com.example.admin.user.dto.AuthProvider;

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
