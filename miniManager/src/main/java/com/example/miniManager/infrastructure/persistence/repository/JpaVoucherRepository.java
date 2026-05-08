package com.example.miniManager.infrastructure.persistence.repository;

import com.example.miniManager.infrastructure.persistence.entity.VoucherDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaVoucherRepository
        extends JpaRepository<VoucherDbEntity, Long> {

    Optional<VoucherDbEntity> findByCode(String code);
}