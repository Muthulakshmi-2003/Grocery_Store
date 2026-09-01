package com.example.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateInventoryRequest(@NotNull Long productId, @NotNull Integer availableQuantity, @NotNull Integer reservedQuantity){
    
}
