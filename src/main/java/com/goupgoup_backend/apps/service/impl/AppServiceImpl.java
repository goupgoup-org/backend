package com.goupgoup_backend.apps.service.impl;

import com.goupgoup_backend.apps.domain.App;
import com.goupgoup_backend.apps.dto.AppRequest;
import com.goupgoup_backend.apps.repository.AppRepository;
import com.goupgoup_backend.apps.service.AppService;
import com.goupgoup_backend.aws.service.AwsS3Service;
import com.goupgoup_backend.common.utils.UidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
    private final AppRepository appRepository;
    private final AwsS3Service awsS3Service;

    @Override
    public Long createApp(AppRequest request, List<MultipartFile> multipartFile) {
        String appVersionUid = UidUtil.generateUid();
        List<String> appLogoUrl = awsS3Service.upload(multipartFile);

        request.setAppLogoUrl(appLogoUrl.get(0));

        App app = appRepository.save(App.from(request, appVersionUid));

        return app.getId();
    }
}
