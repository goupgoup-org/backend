package com.goupgoup_backend.screen.controller;

import com.goupgoup_backend.common.dto.ResponseDto;
import com.goupgoup_backend.common.utils.ResponseUtil;
import com.goupgoup_backend.screen.dto.ScreenRequest;
import com.goupgoup_backend.screen.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/screen")
@RequiredArgsConstructor
public class ScreenController {
    private final ScreenService screenService;

    @PostMapping
    public ResponseDto createScreen(
            @RequestBody ScreenRequest screenRequest
    ) {
       String screenId =  screenService.createScreen(screenRequest);

        return ResponseUtil.SUCCESS("화면 등록에 성공하였습니다.", screenId);
    }
}
