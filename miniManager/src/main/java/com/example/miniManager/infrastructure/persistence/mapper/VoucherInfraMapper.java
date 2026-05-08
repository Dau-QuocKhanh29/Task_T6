package com.example.miniManager.infrastructure.persistence.mapper;

import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.infrastructure.persistence.entity.VoucherDbEntity;

public class VoucherInfraMapper {

    public static VoucherDbEntity toEntity(Voucher voucher) {

        VoucherDbEntity entity = new VoucherDbEntity();

        entity.setId(voucher.getId());
        entity.setCode(voucher.getCode());
        entity.setDiscountPercent(voucher.getDiscountPercent());
        entity.setQuantity(voucher.getQuantity());
        entity.setExpiredDate(voucher.getExpiredDate());
        entity.setStatus(voucher.getStatus());
        entity.setCreatedAt(voucher.getCreatedAt());

        return entity;
    }

    public static Voucher toDomain(VoucherDbEntity entity) {

        return new Voucher(
                entity.getId(),
                entity.getCode(),
                entity.getDiscountPercent(),
                entity.getQuantity(),
                entity.getExpiredDate(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}