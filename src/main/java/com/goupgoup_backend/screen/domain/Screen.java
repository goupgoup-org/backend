package com.goupgoup_backend.screen.domain;

import com.goupgoup_backend.apps.domain.App;
import com.goupgoup_backend.common.domain.BaseEntity;
import com.goupgoup_backend.screen.dto.ScreenRequest;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Screen extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name="screen_id", columnDefinition = "Binary(16)")
    private String id;
    private String screenName; // 화면 구분
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="apps_id")
    private App app;

    public static Screen from(ScreenRequest request) {
        return Screen.builder()
                .screenName(request.getScreenName())
                .description(request.getDescription())
                .build();
    }
}
