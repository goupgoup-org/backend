package com.goupgoup_backend.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SignUpType {
    KAKAO("kakao"),
    GOOGLE("google"),
    FACEBOOK("facebook");

    private final String type;
}
