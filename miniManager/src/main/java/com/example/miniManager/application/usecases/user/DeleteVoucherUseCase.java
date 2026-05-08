package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.repository.IVoucherRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteVoucherUseCase {

    private final IVoucherRepository voucherRepository;

    public DeleteVoucherUseCase(IVoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public void execute(Long id) {

        voucherRepository.deleteById(id);
    }
}