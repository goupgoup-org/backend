package com.goupgoup_backend.apps.domain;

import com.goupgoup_backend.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Screen extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String screenName;
    private String description;
    private Long screenNumber; // 화면 순서
    private String screenUrl;
    private String page; // 화면 구분

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="apps_id")
    private App app;

}
