package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMovementRepo extends JpaRepository<StockMovement,Long> {
    List<StockMovement> findByProductId(Long productId);
}
