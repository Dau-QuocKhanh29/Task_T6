package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.domain.repository.IVoucherRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateVoucherUseCase {

    private final IVoucherRepository voucherRepository;

    public UpdateVoucherUseCase(IVoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public Voucher execute(Long id, Voucher request) {

        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Voucher không tồn tại"));

        voucher.setCode(request.getCode());
        voucher.setDiscountPercent(request.getDiscountPercent());
        voucher.setQuantity(request.getQuantity());
        voucher.setExpiredDate(request.getExpiredDate());
        voucher.setStatus(request.getStatus());

        return voucherRepository.save(voucher);
    }
}