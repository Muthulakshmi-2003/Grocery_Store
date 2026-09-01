package com.example.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;

public record CreateInventoryRequest (@NotNull Long productId , @NotNull Integer availableQuantity){

}
