package com.example.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReserveStockRequest(@NotNull Long productId, @Positive Integer quantity){
}
