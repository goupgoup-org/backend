package com.goupgoup_backend.admin.user.controller;

import com.goupgoup_backend.admin.user.dto.UserRequest;
import com.goupgoup_backend.admin.user.service.AdminUserService;
import com.goupgoup_backend.common.dto.ResponseDto;
import com.goupgoup_backend.common.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;
    @GetMapping("/sign_in")
    public ResponseDto signIn(
            @RequestBody UserRequest parameter
    ) {


        return ResponseUtil.SUCCESS("로그인 성공", true);
    }

    @PostMapping("/sign_up")
    public ResponseDto signUp(
            @RequestBody UserRequest parameter
    ) {
        boolean result = adminUserService.signUp(parameter);

        if(result) {
            return ResponseUtil.SUCCESS("회원가입 성공", true);
        } else {
            return ResponseUtil.FAILURE("회원가입 실패", false);
        }

    }
}
