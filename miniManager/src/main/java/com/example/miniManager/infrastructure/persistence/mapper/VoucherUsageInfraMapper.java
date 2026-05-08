package com.example.miniManager.infrastructure.persistence.mapper;

import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.domain.model.VoucherUsage;
import com.example.miniManager.infrastructure.persistence.entity.VoucherUsageDbEntity;

public class VoucherUsageInfraMapper {

    public static VoucherUsageDbEntity toEntity(VoucherUsage usage) {

        VoucherUsageDbEntity entity = new VoucherUsageDbEntity();

        entity.setId(usage.getId());
        entity.setUserId(usage.getUserId());
        entity.setVoucherId(usage.getVoucherId());
        entity.setUsedAt(usage.getUsedAt());

        return entity;
    }

    public static VoucherUsage  toDomain(VoucherUsageDbEntity entity) {

        return new VoucherUsage(
                entity.getId(),
                entity.getUserId(),
                entity.getVoucherId(),
                entity.getUsedAt()
        );
    }
}