package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.model.VoucherUsage;
import com.example.miniManager.domain.repository.IVoucherUsageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetVoucherUsageUseCase {

    private final IVoucherUsageRepository repository;

    public GetVoucherUsageUseCase(
            IVoucherUsageRepository repository) {

        this.repository = repository;
    }

    public List<VoucherUsage> execute() {

        return repository.findAll();
    }
}