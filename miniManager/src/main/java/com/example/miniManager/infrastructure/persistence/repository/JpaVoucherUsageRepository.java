package com.example.miniManager.infrastructure.persistence.repository;

import com.example.miniManager.infrastructure.persistence.entity.VoucherUsageDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVoucherUsageRepository
        extends JpaRepository<VoucherUsageDbEntity, Long> {
}