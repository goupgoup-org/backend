package com.example.admin.user.service;

import com.example.admin.user.domain.User;
import com.example.admin.user.dto.UserResponse;
import com.example.admin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse getUser(String email) {
        if(email == null) {
            throw new IllegalArgumentException("email is null");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        return UserResponse.from(user);
    }
}
