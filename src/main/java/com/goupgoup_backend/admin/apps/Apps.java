package com.goupgoup_backend.admin.apps;

import com.goupgoup_backend.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Apps extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appVersionId;

    // 앱 이름
    private String appName;
    // 앱로고 이미지
    private String appLogoUrl;
    // 앱 소개글
    private String appDescription;


    @Enumerated(EnumType.STRING)
    private PlatformType platformType;


}
