package com.example.admin.apps.repository;

import com.example.admin.apps.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {
}
