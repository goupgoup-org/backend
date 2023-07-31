package com.example.admin.screen.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenImgRequest {
    private String imagePath;
    private Integer imgSeq;
}