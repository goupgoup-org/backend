package com.example.admin.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRequest {
    private String email;
    private String nickname;
    private String serviceId;
}
