package com.example.miniManager.domain.repository;

import com.example.miniManager.domain.model.VoucherUsage;

import java.util.List;

public interface IVoucherUsageRepository {

    VoucherUsage save(VoucherUsage voucherUsage);

    List<VoucherUsage> findAll();
}