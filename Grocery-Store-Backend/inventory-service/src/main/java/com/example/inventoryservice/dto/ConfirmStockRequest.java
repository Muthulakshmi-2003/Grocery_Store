package com.example.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;

public record ConfirmStockRequest(@NotNull Long productId, @NotNull Integer quantity) {
}
