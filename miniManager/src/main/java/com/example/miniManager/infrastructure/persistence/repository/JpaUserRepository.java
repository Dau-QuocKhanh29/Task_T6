package com.example.miniManager.infrastructure.persistence.repository;

import com.example.miniManager.infrastructure.persistence.entity.UserDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserDbEntity, Long> {
    Optional<UserDbEntity> findByEmail(String email);
}