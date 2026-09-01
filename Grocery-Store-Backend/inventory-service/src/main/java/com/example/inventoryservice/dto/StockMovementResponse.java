package com.example.inventoryservice.dto;

import com.example.inventoryservice.entity.MovementType;

import java.time.LocalDateTime;

public record StockMovementResponse(Long id, Long productId, Integer quantity, MovementType movementType, LocalDateTime createdAt) {
}
