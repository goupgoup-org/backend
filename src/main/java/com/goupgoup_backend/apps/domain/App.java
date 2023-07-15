package com.goupgoup_backend.apps.domain;

import com.goupgoup_backend.apps.dto.AppRequest;
import com.goupgoup_backend.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class App extends BaseEntity {
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

//    @OneToMany(mappedBy = "apps", cascade = CascadeType.ALL)
//    private List<Screen> screens;

    @Enumerated(EnumType.STRING)
    private PlatformType platformType;

    public static App from (AppRequest request, String appVersionId) {
        return App.builder()
                .appVersionId(appVersionId)
                .appName(request.getAppName())
                .appLogoUrl(request.getAppLogoUrl())
                .appDescription(request.getAppDescription())
                .build();
    }
}
