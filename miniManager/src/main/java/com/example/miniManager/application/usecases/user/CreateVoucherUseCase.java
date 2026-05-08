package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.domain.repository.IVoucherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CreateVoucherUseCase {

    private final IVoucherRepository voucherRepository;

    public CreateVoucherUseCase(IVoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public Voucher execute(Voucher voucher) {

        if (voucherRepository.findByCode(voucher.getCode()).isPresent()) {
            throw new RuntimeException("Code voucher đã tồn tại");
        }

        if (voucher.getDiscountPercent() < 1
                || voucher.getDiscountPercent() > 100) {

            throw new RuntimeException("discount_percent phải từ 1 -> 100");
        }

        if (voucher.getQuantity() < 0) {
            throw new RuntimeException("quantity phải >= 0");
        }

        if (voucher.getExpiredDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("expired_date phải lớn hơn hiện tại");
        }

        voucher.setCreatedAt(LocalDateTime.now());

        return voucherRepository.save(voucher);
    }
}