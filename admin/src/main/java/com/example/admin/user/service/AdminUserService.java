package com.example.admin.user.service;


import com.example.admin.user.domain.User;
import com.example.admin.user.dto.UserRequest;
import com.example.admin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인
     * @param parameter
     * @return
     */
    public boolean login(UserRequest parameter) {
        return true;
    }

    /**
     * 회원가입
     * @param parameter
     * @return
     */
    public boolean signUp(UserRequest parameter) {
        Optional<User> optionalUser = userRepository.findByEmail(parameter.getEmail());

        if(optionalUser.isPresent()) {
            return false;
        }

        String encPassword = passwordEncoder.encode(parameter.getPassword());

        User user = User.builder()
                .email(parameter.getEmail())
                .password(encPassword)
                .build();

        userRepository.save(user);

        return true;
    }

    public String findByUserId() {
        return null;
    }
}
