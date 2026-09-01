package com.example.inventoryservice.service.Implementataion;

import com.example.inventoryservice.dto.StockMovementResponse;
import com.example.inventoryservice.entity.MovementType;
import com.example.inventoryservice.entity.StockMovement;
import com.example.inventoryservice.exception.StockMovementNotFound;
import com.example.inventoryservice.repository.StockMovementRepo;
import com.example.inventoryservice.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepo stockMovementRepo;


    @Override
    public List<StockMovementResponse> getAll() {
        return stockMovementRepo.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<StockMovementResponse> getByProductId(Long productId) {
        List<StockMovement> movements = stockMovementRepo.findByProductId(productId);

        if (movements.isEmpty()) {
            throw new StockMovementNotFound(productId);
        }

        return movements.stream()
                .map(this::map)
                .toList();
    }

    private StockMovementResponse map(StockMovement stockMovement){
        return new StockMovementResponse(
                stockMovement.getId(),
                stockMovement.getProductId(),
                stockMovement.getQuantity(),
                stockMovement.getMovementType(),
                stockMovement.getCreatedAt()
        );
    }

    @Override
    public void createMovement(Long productId, Integer quantity, MovementType movementType) {

        StockMovement movement = StockMovement.builder()
                .productId(productId)
                .quantity(quantity)
                .movementType(movementType)
                .createdAt(LocalDateTime.now())
                .build();

        stockMovementRepo.save(movement);
    }
}
