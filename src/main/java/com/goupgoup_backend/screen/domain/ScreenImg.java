package com.goupgoup_backend.screen.domain;

import com.goupgoup_backend.common.domain.BaseEntity;
import com.goupgoup_backend.screen.dto.ScreenImgRequest;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ScreenImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    private String imgUrl;

    private Integer imgSeq;

    public static ScreenImg from(Screen screen, String imgUrl, Integer imgSeq) {
        return ScreenImg.builder()
                .screen(screen)
                .imgUrl(imgUrl)
                .imgSeq(imgSeq)
                .build();
    }
}
