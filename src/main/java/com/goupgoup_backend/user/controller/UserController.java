package com.goupgoup_backend.user.controller;

import com.goupgoup_backend.common.dto.ResponseDto;
import com.goupgoup_backend.common.utils.ResponseUtil;
import com.goupgoup_backend.user.dto.UserResponse;
import com.goupgoup_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/api/user/oauth")
    public ResponseDto getUser(
            @AuthenticationPrincipal User user
    ) {
        UserResponse userResponse = userService.getUser(user.getUsername());

        return ResponseUtil.SUCCESS("유저 조회 성공", userResponse);
    }
}
