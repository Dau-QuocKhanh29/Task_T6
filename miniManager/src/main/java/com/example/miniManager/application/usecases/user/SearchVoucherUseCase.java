package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.domain.repository.IVoucherRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchVoucherUseCase {

    private final IVoucherRepository voucherRepository;

    public SearchVoucherUseCase(IVoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public Voucher execute(String code) {

        return voucherRepository.findByCode(code)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy voucher"));
    }
}