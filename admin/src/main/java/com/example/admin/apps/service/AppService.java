package com.example.admin.apps.service;

import com.example.admin.apps.dto.AppRequest;
import org.springframework.web.multipart.MultipartFile;

public interface AppService {
    Long createApp(AppRequest request, MultipartFile multipartFile);
}
