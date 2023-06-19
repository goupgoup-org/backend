package com.goupgoup_backend.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class SignUpRequest {
    private String email;
    private String nickname;
    private String serviceId;
}
