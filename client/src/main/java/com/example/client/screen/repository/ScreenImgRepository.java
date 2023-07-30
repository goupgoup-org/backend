package com.example.client.screen.repository;

import com.goupgoup_backend.screen.domain.ScreenImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenImgRepository extends JpaRepository<ScreenImg, Long> {
}
