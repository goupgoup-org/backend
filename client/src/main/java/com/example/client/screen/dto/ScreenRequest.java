package com.example.client.screen.dto;

import com.goupgoup_backend.common.domain.BaseEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenRequest extends BaseEntity {
    private String screenName;
    private String description;

    private List<ScreenImgRequest> screenImgRequestList;
}
