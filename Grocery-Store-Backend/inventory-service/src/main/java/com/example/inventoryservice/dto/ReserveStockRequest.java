package com.example.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;

public record ReserveStockRequest(@NotNull Long productId, @NotNull Integer quantity){
}
