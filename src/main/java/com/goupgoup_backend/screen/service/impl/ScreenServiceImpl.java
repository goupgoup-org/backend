package com.goupgoup_backend.screen.service.impl;

import com.goupgoup_backend.aws.service.AwsS3Service;
import com.goupgoup_backend.screen.domain.Screen;
import com.goupgoup_backend.screen.domain.ScreenImg;
import com.goupgoup_backend.screen.dto.ScreenImgRequest;
import com.goupgoup_backend.screen.dto.ScreenRequest;
import com.goupgoup_backend.screen.repository.ScreenImgRepository;
import com.goupgoup_backend.screen.repository.ScreenRepository;
import com.goupgoup_backend.screen.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService {
    private final AwsS3Service awsS3Service;
    private final ScreenRepository screenRepository;
    private final ScreenImgRepository screenImgRepository;

    @Override
    public String createScreen(ScreenRequest screenRequest) {
        Screen screen = Screen.from(screenRequest);

        List<ScreenImg> screenImgList = new ArrayList<>();
        for (ScreenImgRequest screenImgRequest : screenRequest.getScreenImgRequestList()) {
            String screenImgUrl = awsS3Service.upload(screenImgRequest.getImageFile(), screen.getId());
            ScreenImg screenImg = ScreenImg.from(screen, screenImgUrl, screenImgRequest.getImgSeq());
            screenImgList.add(screenImg);
        }

        screenImgRepository.saveAll(screenImgList);

        return screenRepository.save(screen).getId();
    }

    public void saveScreenImg(List<String> screenImageUrlList) {


    }
}
