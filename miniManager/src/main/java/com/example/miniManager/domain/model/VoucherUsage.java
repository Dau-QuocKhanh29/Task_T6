package com.example.miniManager.domain.model;

import java.time.LocalDateTime;

public class VoucherUsage {

    private Long id;

    private Long userId;

    private Long voucherId;

    private LocalDateTime usedAt;

    public VoucherUsage() {
    }

    public VoucherUsage(Long id,
                        Long userId,
                        Long voucherId,
                        LocalDateTime usedAt) {

        this.id = id;
        this.userId = userId;
        this.voucherId = voucherId;
        this.usedAt = usedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(LocalDateTime usedAt) {
        this.usedAt = usedAt;
    }
}