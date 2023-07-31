package com.example.admin.screen.dto;

import com.example.admin.common.domain.BaseEntity;
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
