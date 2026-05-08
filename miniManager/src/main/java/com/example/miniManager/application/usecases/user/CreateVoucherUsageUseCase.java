package com.example.miniManager.application.usecases.user;

import com.example.miniManager.domain.model.User;
import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.domain.model.VoucherUsage;
import com.example.miniManager.domain.repository.IUserRepository;
import com.example.miniManager.domain.repository.IVoucherRepository;
import com.example.miniManager.domain.repository.IVoucherUsageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CreateVoucherUsageUseCase {

    private final IUserRepository userRepository;

    private final IVoucherRepository voucherRepository;

    private final IVoucherUsageRepository usageRepository;

    public CreateVoucherUsageUseCase(
            IUserRepository userRepository,
            IVoucherRepository voucherRepository,
            IVoucherUsageRepository usageRepository) {

        this.userRepository = userRepository;
        this.voucherRepository = voucherRepository;
        this.usageRepository = usageRepository;
    }

    public VoucherUsage execute(Long userId,
                                Long voucherId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User không tồn tại"));

        Voucher voucher = voucherRepository.findById(voucherId)
                .orElseThrow(() ->
                        new RuntimeException("Voucher không tồn tại"));

        // VALIDATE

        if (voucher.getExpiredDate().isBefore(LocalDate.now())) {

            throw new RuntimeException("Voucher đã hết hạn");
        }

        if (!voucher.getStatus().equalsIgnoreCase("ACTIVE")) {

            throw new RuntimeException("Voucher INACTIVE");
        }

        if (voucher.getQuantity() <= 0) {

            throw new RuntimeException("Voucher đã hết số lượng");
        }

        // GIẢM quantity

        voucher.setQuantity(voucher.getQuantity() - 1);

        voucherRepository.save(voucher);

        // TẠO lịch sử

        VoucherUsage usage = new VoucherUsage();

        usage.setUserId(user.getId());

        usage.setVoucherId(voucher.getId());

        usage.setUsedAt(LocalDateTime.now());

        return usageRepository.save(usage);
    }
}