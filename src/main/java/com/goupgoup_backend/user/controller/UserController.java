package com.goupgoup_backend.user.controller;

import com.goupgoup_backend.user.dto.TokenDto;
import com.goupgoup_backend.user.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomOAuth2UserService customOAuth2UserService;

    @GetMapping("/api/user/oauth")
    public ResponseEntity<?> kakaoCallback(
            @RequestParam String code
    ) throws IOException {
        TokenDto tokenDto = customOAuth2UserService.kakaoSignIn(code);

        return ResponseEntity.ok(tokenDto);
    }
}
