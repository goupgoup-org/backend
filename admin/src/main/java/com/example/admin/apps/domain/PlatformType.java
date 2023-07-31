package com.example.admin.apps.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlatformType {
    iOS("iOS"),
    Android("Android"),
    Web("Web");

    private final String type;
}
