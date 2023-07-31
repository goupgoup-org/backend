package com.example.admin.screen.service;


import com.example.admin.screen.dto.ScreenRequest;

import java.io.IOException;

public interface ScreenService {
    Long createScreen(ScreenRequest screenRequest) throws IOException;
}
