package com.example.miniManager.domain.repository;

import com.example.miniManager.domain.model.Voucher;

import java.util.List;
import java.util.Optional;

public interface IVoucherRepository {

    Voucher save(Voucher voucher);

    List<Voucher> findAll();

    Optional<Voucher> findById(Long id);

    Optional<Voucher> findByCode(String code);

    void deleteById(Long id);
}