package com.example.client.screen.service.impl;

import com.goupgoup_backend.screen.domain.Screen;
import com.goupgoup_backend.screen.domain.ScreenImg;
import com.goupgoup_backend.screen.dto.ScreenImgRequest;
import com.goupgoup_backend.screen.dto.ScreenRequest;
import com.goupgoup_backend.screen.repository.ScreenImgRepository;
import com.goupgoup_backend.screen.repository.ScreenRepository;
import com.goupgoup_backend.screen.service.ScreenService;
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
