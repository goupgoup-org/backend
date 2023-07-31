package com.example.admin.apps.service.impl;

import com.example.admin.apps.domain.App;
import com.example.admin.apps.dto.AppRequest;
import com.example.admin.apps.repository.AppRepository;
import com.example.admin.apps.service.AppService;
import com.example.admin.common.utils.UidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
    private final AppRepository appRepository;
//    private final AwsS3Service awsS3Service;

    @Override
    public Long createApp(AppRequest request, MultipartFile multipartFile) {
        String appVersionUid = UidUtil.generateUid();
//        String appLogoUrl = awsS3Service.upload(multipartFile, request.getAppName());
//        request.setAppLogoUrl(appLogoUrl);

        App app = appRepository.save(App.from(request, appVersionUid));

        return app.getId();
    }
}
