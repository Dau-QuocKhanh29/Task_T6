package com.example.miniManager.interfaces.rest;

import com.example.miniManager.application.usecases.user.*;
import com.example.miniManager.domain.model.Voucher;
import com.example.miniManager.interfaces.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {

    private final CreateVoucherUseCase createVoucherUseCase;
    private final UpdateVoucherUseCase updateVoucherUseCase;
    private final DeleteVoucherUseCase deleteVoucherUseCase;
    private final GetVoucherUseCase getVoucherUseCase;
    private final SearchVoucherUseCase searchVoucherUseCase;

    public VoucherController(
            CreateVoucherUseCase createVoucherUseCase,
            UpdateVoucherUseCase updateVoucherUseCase,
            DeleteVoucherUseCase deleteVoucherUseCase,
            GetVoucherUseCase getVoucherUseCase,
            SearchVoucherUseCase searchVoucherUseCase) {

        this.createVoucherUseCase = createVoucherUseCase;
        this.updateVoucherUseCase = updateVoucherUseCase;
        this.deleteVoucherUseCase = deleteVoucherUseCase;
        this.getVoucherUseCase = getVoucherUseCase;
        this.searchVoucherUseCase = searchVoucherUseCase;
    }

    @GetMapping
    public ApiResponse<List<Voucher>> getAll() {

        return new ApiResponse<>(
                true,
                "Get vouchers successfully",
                getVoucherUseCase.execute()
        );
    }

    @PostMapping
    public ApiResponse<Voucher> create(
            @RequestBody Voucher voucher) {

        Voucher result =
                createVoucherUseCase.execute(voucher);

        return new ApiResponse<>(
                true,
                "Create voucher successfully",
                result
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Voucher> update(
            @PathVariable Long id,
            @RequestBody Voucher voucher) {

        Voucher result =
                updateVoucherUseCase.execute(id, voucher);

        return new ApiResponse<>(
                true,
                "Update voucher successfully",
                result
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> delete(
            @PathVariable Long id) {

        deleteVoucherUseCase.execute(id);

        return new ApiResponse<>(
                true,
                "Delete voucher successfully",
                null
        );
    }

    @GetMapping("/search")
    public ApiResponse<Voucher> search(
            @RequestParam String code) {

        Voucher result =
                searchVoucherUseCase.execute(code);

        return new ApiResponse<>(
                true,
                "Search voucher successfully",
                result
        );
    }
}