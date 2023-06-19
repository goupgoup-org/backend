package com.goupgoup_backend.user.dto;

import lombok.*;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String email;
    private String nickname;
    private String accessToken;

    public static UserResponse of(OAuth2User oAuth2User) {
        return UserResponse.builder()
                .email(oAuth2User.getAttribute("email"))
                .nickname(oAuth2User.getAttribute("nickname"))
                .accessToken(oAuth2User.getAttribute("accessToken"))
                .build();
    }
}
