package com.example.admin.screen.repository;

import com.example.admin.screen.domain.ScreenImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenImgRepository extends JpaRepository<ScreenImg, Long> {
}
