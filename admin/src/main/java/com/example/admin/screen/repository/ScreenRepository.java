package com.example.admin.screen.repository;

import com.example.admin.screen.domain.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
