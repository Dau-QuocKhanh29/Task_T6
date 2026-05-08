package com.example.miniManager.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Voucher {

    private Long id;
    private String code;
    private Integer discountPercent;
    private Integer quantity;
    private LocalDate expiredDate;
    private String status;
    private LocalDateTime createdAt;

    public Voucher() {
    }

    public Voucher(Long id,
                   String code,
                   Integer discountPercent,
                   Integer quantity,
                   LocalDate expiredDate,
                   String status,
                   LocalDateTime createdAt) {

        this.id = id;
        this.code = code;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.expiredDate = expiredDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}