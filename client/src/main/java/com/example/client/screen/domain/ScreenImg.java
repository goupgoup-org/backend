package com.example.client.screen.domain;

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
//    @JoinColumn(name = "id")
    private Screen screen;

    private String imagePath;

    private Integer imgSeq;

    public static ScreenImg from(Screen screen, ScreenImgRequest request) {
        return ScreenImg.builder()
                .screen(screen)
                .imagePath(request.getImagePath())
                .imgSeq(request.getImgSeq())
                .build();
    }
}
