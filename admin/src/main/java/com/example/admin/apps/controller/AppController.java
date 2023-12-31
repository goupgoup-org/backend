package com.example.admin.apps.controller;

import com.example.admin.apps.dto.AppRequest;
import com.example.admin.apps.service.AppService;
import com.example.admin.common.dto.ResponseDto;
import com.example.admin.common.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/app")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @PostMapping
        public ResponseDto createApp(
                @RequestPart(value = "request") AppRequest request,
                @RequestPart(value = "appIcon") MultipartFile multipartFile
    ) {
        Long appId = appService.createApp(request, multipartFile);

        return ResponseUtil.SUCCESS("앱 생성 성공", appId);
    }
}
