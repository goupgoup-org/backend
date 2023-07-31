package com.example.admin.screen.controller;

import com.example.admin.common.dto.ResponseDto;
import com.example.admin.common.utils.ResponseUtil;
import com.example.admin.screen.dto.ScreenRequest;
import com.example.admin.screen.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/screen")
@RequiredArgsConstructor
public class ScreenController {
    private final ScreenService screenService;

    @PostMapping
    public ResponseDto createScreen(
           @RequestBody ScreenRequest screenRequest
    ) throws IOException {
        Long screenId =  screenService.createScreen(screenRequest);

        return ResponseUtil.SUCCESS("화면 등록에 성공하였습니다.", screenId);
    }
}
