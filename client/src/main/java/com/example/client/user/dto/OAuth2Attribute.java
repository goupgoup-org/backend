package com.example.client.user.dto;

import com.goupgoup_backend.user.domain.User;
import com.goupgoup_backend.user.domain.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class OAuth2Attribute {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String name;
    private String picture;
    private AuthProvider provider;

    public static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
       switch (provider) {
           case "google":
               return ofGoogle(attributeKey, attributes);
           case "kakao":
               return ofKakao("email", attributes);
           default:
               throw new RuntimeException();
       }
    }

    private static OAuth2Attribute ofGoogle(String attributeKey, Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
                .name(attributes.get("name").toString())
                .email(attributes.get("email").toString())
                .provider(AuthProvider.GOOGLE)
                .picture(attributes.get("picture").toString())
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2Attribute ofKakao(String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .name(kakaoProfile.get("nickname").toString())
                .email(kakaoAccount.get("email").toString())
                .picture(kakaoProfile.get("profile_image_url").toString())
                .provider(AuthProvider.KAKAO)
                .attributes(kakaoAccount)
                .attributeKey(attributeKey)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .authProvider(provider)
                .role(UserRole.USER)
                .build();
    }

    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("name", name);
        map.put("email", email);
        map.put("picture", picture);

        return map;
    }
}
