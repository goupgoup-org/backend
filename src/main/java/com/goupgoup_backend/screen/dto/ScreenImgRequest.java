package com.goupgoup_backend.screen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreenImgRequest {
    private MultipartFile imageFile;
    private Integer imgSeq;

}