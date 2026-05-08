package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.domain.repository.IVoucherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetVoucherUseCase {

    private final IVoucherRepository voucherRepository;

    public GetVoucherUseCase(IVoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public List<Voucher> execute() {

        return voucherRepository.findAll();
    }
}