package com.goupgoup_backend.apps.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppRequest {
    private String appName;
    private String appLogoUrl;
    private String appDescription;
    private String platformType;
}
