package com.example.admin.screen.service.impl;

import com.example.admin.screen.domain.Screen;
import com.example.admin.screen.domain.ScreenImg;
import com.example.admin.screen.dto.ScreenImgRequest;
import com.example.admin.screen.dto.ScreenRequest;
import com.example.admin.screen.repository.ScreenImgRepository;
import com.example.admin.screen.repository.ScreenRepository;
import com.example.admin.screen.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService {
    private final ScreenRepository screenRepository;
    private final ScreenImgRepository screenImgRepository;

    @Override
    public Long createScreen(ScreenRequest screenRequest) throws IOException {

        Screen screen = screenRepository.save(Screen.from(screenRequest));

        List<ScreenImg> screenImgList = new ArrayList<>();

        for (ScreenImgRequest screenImgRequest : screenRequest.getScreenImgRequestList()) {
            screenImgList.add(ScreenImg.from(screen, screenImgRequest));
        }

        screenImgRepository.saveAll(screenImgList);
        return screen.getId();
    }

    public void saveScreenImg(List<String> screenImageUrlList) {


    }
}
