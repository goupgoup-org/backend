package com.goupgoup_backend.user.service;

import com.goupgoup_backend.user.domain.User;
import com.goupgoup_backend.user.dto.SignUpRequest;
import com.goupgoup_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long signUp(SignUpRequest signUpRequest) {
        Long id;

        try{
            id = userRepository.save(User.from(signUpRequest)).getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    public SignUpRequest getUserKakaoSignupRequest(HashMap<String, Object> userInfo) {
        SignUpRequest request = SignUpRequest.builder()
                .email(userInfo.get("email").toString())
                .nickname(userInfo.get("nickname").toString())
                .build();

        return request;
    }

}
