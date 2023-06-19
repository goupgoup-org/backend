package com.goupgoup_backend.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.goupgoup_backend.common.config.JwtTokenProvider;
import com.goupgoup_backend.user.domain.SignUpType;
import com.goupgoup_backend.user.domain.User;
import com.goupgoup_backend.user.dto.SignUpRequest;
import com.goupgoup_backend.user.dto.TokenDto;
import com.goupgoup_backend.user.dto.UserResponse;
import com.goupgoup_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService {
    @Value("${kakao.client-id}")
    private String clientId;
    @Value("${kakao.redirect-uri}")
    private String redirectURI;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenDto kakaoSignIn(String code) throws IOException {
        String accessToken = getAccessToken(code, redirectURI);

        User user = signUpKakaoUser(accessToken);

        String userAccessToken = jwtTokenProvider.createAccessToken(user.getEmail(), List.of("ROLE_USER"));
        String userRefreshToken = jwtTokenProvider.createRefreshToken(user.getEmail(), List.of("ROLE_USER"));


        return jwtTokenProvider.createTokenDto(userAccessToken, userRefreshToken);
    }


    public User signUpKakaoUser(String accessToken) throws IOException {
        JsonNode jsonNode = getKakaoUserInfo(accessToken);

        String kakaoId = String.valueOf(jsonNode.get("id"));
        String kakaoUserAccountJson = String.valueOf(jsonNode.get("kakao_account"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode accountValue = objectMapper.readTree(kakaoUserAccountJson);

        String kakaoUserEmail = String.valueOf(accountValue.get("email").asText());
        User user = userRepository.findByEmail(kakaoUserEmail)
                .orElse(null);
//                .orElseThrow(() -> new RuntimeException("해당 서비스 회원을 찾을 수 없습니다."));

        if(user == null) {
            String kakaoNickname = jsonNode.get("properties").get("nickname").asText();
            SignUpRequest parameter = SignUpRequest.builder()
                    .email(kakaoUserEmail)
                    .nickname(kakaoNickname)
                    .build();

            user = userRepository.save(User.from(parameter));
        }

        return user;
    }

    public JsonNode getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(responseBody);
    }

    private String getAccessToken(String code, String redirectUri) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);


        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return jsonNode.get("access_token").asText();
    }
}
