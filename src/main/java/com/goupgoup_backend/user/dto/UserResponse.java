package com.goupgoup_backend.user.dto;

import com.goupgoup_backend.user.domain.User;
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

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .nickname(user.getName())
                .build();
    }
}
