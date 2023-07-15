package com.goupgoup_backend.apps.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenRequest {
    private String screenName;
    private String description;
    private Long screenNumber;
    private String screenUrl;
    private String page;
}
