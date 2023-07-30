package com.example.client.apps.service;

import com.goupgoup_backend.apps.dto.AppRequest;
import org.springframework.web.multipart.MultipartFile;

public interface AppService {
    Long createApp(AppRequest request, MultipartFile multipartFile);
}
