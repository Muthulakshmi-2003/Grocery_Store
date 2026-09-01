package com.example.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    private LocalDateTime createdAt;

}
