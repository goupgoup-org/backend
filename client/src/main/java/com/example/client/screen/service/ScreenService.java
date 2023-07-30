package com.example.client.screen.service;

import com.goupgoup_backend.screen.dto.ScreenRequest;

import java.io.IOException;

public interface ScreenService {
    Long createScreen(ScreenRequest screenRequest) throws IOException;
}
