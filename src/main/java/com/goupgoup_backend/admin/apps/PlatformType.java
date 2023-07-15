package com.goupgoup_backend.admin.apps;

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
