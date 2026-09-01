package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.StockMovementResponse;
import com.example.inventoryservice.entity.MovementType;

import java.util.List;

public interface StockMovementService {

    List<StockMovementResponse> getAll();

   List< StockMovementResponse> getByProductId(Long productId);

    void createMovement(Long productId, Integer quantity, MovementType movementType);
}
