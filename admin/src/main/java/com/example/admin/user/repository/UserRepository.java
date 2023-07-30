package com.example.admin.user.repository;


import com.example.admin.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
//    Optional<User> findByTypeAndEmail(SignUpType type, String email);
}
