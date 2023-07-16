package com.goupgoup_backend.apps.service;

import com.goupgoup_backend.apps.dto.AppRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AppService {
    Long createApp(AppRequest request, MultipartFile multipartFile);
}
