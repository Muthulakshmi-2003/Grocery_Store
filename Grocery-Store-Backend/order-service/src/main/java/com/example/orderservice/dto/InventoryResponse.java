package com.example.orderservice.dto;

public record InventoryResponse(Long id, Long productId, Integer availableQuantity, Integer reservedQuantity) {



}
