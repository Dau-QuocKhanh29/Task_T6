package com.example.miniManager.infrastructure.persistence.repository;

import com.example.miniManager.domain.model.VoucherUsage;
import com.example.miniManager.domain.repository.IVoucherUsageRepository;
import com.example.miniManager.infrastructure.persistence.mapper.VoucherUsageInfraMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VoucherUsageRepositoryImpl
        implements IVoucherUsageRepository {

    private final JpaVoucherUsageRepository repository;

    public VoucherUsageRepositoryImpl(
            JpaVoucherUsageRepository repository) {

        this.repository = repository;
    }

    @Override
    public VoucherUsage save(VoucherUsage usage) {

        return VoucherUsageInfraMapper.toDomain(
                repository.save(
                        VoucherUsageInfraMapper.toEntity(usage)
                )
        );
    }

    @Override
    public List<VoucherUsage> findAll() {

        return repository.findAll()
                .stream()
                .map(VoucherUsageInfraMapper::toDomain)
                .collect(Collectors.toList());
    }
}