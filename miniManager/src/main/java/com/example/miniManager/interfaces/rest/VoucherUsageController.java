package com.example.miniManager.interfaces.rest;

import com.example.miniManager.application.usecases.user.CreateVoucherUsageUseCase;
import com.example.miniManager.application.usecases.user.GetVoucherUsageUseCase;
import com.example.miniManager.domain.model.VoucherUsage;
import com.example.miniManager.interfaces.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voucher-usages")
public class VoucherUsageController {

    private final CreateVoucherUsageUseCase createUseCase;

    private final GetVoucherUsageUseCase getUseCase;

    public VoucherUsageController(
            CreateVoucherUsageUseCase createUseCase,
            GetVoucherUsageUseCase getUseCase) {

        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
    }

    @PostMapping
    public ApiResponse<VoucherUsage> useVoucher(
            @RequestParam Long userId,
            @RequestParam Long voucherId) {

        VoucherUsage usage =
                createUseCase.execute(userId, voucherId);

        return new ApiResponse<>(
                true,
                "Use voucher successfully",
                usage
        );
    }

    @GetMapping
    public ApiResponse<List<VoucherUsage>> getAll() {

        return new ApiResponse<>(
                true,
                "Get voucher usages successfully",
                getUseCase.execute()
        );
    }
}