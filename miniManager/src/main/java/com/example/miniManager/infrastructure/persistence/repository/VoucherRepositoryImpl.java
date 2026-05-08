package com.example.miniManager.infrastructure.persistence.repository;

import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.domain.repository.IVoucherRepository;
import com.example.miniManager.infrastructure.persistence.mapper.VoucherInfraMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VoucherRepositoryImpl implements IVoucherRepository {

    private final JpaVoucherRepository jpaVoucherRepository;

    public VoucherRepositoryImpl(JpaVoucherRepository jpaVoucherRepository) {
        this.jpaVoucherRepository = jpaVoucherRepository;
    }

    @Override
    public Voucher save(Voucher voucher) {
        return VoucherInfraMapper.toDomain(
                jpaVoucherRepository.save(
                        VoucherInfraMapper.toEntity(voucher)
                )
        );
    }

    @Override
    public List<Voucher> findAll() {
        return jpaVoucherRepository.findAll()
                .stream()
                .map(VoucherInfraMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return jpaVoucherRepository.findById(id)
                .map(VoucherInfraMapper::toDomain);
    }

    @Override
    public Optional<Voucher> findByCode(String code) {
        return jpaVoucherRepository.findByCode(code)
                .map(VoucherInfraMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaVoucherRepository.deleteById(id);
    }
}