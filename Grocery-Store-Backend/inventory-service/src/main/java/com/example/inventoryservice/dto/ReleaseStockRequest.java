package com.example.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;

public record ReleaseStockRequest (@NotNull Long productId, @NotNull Integer quantity) {


}
