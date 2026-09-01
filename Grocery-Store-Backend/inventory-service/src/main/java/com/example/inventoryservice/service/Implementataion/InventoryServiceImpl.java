package com.example.inventoryservice.service.Implementataion;

import com.example.inventoryservice.dto.*;
import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.entity.MovementType;
import com.example.inventoryservice.entity.StockMovement;
import com.example.inventoryservice.exception.InsufficientStockException;
import com.example.inventoryservice.exception.InvalidQuantityException;
import com.example.inventoryservice.exception.InventoryAlreadyExists;
import com.example.inventoryservice.exception.InventoryNotFoundException;
import com.example.inventoryservice.repository.InventoryRepo;
import com.example.inventoryservice.repository.StockMovementRepo;
import com.example.inventoryservice.service.InventoryService;
import com.example.inventoryservice.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl  implements InventoryService {

    private final InventoryRepo inventoryRepo;

    private  final StockMovementRepo stockMovementRepo;

    private final StockMovementService stockMovementService;



    @Override
    public InventoryResponse create(CreateInventoryRequest request) {
        if (inventoryRepo.findByProductId(request.productId()).isPresent()) {
            throw new RuntimeException(
                   new  InventoryAlreadyExists(request.productId()));
        }
        if(request.availableQuantity() < 0){
            throw  new InvalidQuantityException(request.availableQuantity());
        }


       Inventory inventory = Inventory.builder()
               .productId(request.productId())
               .availableQuantity(request.availableQuantity())
               .reservedQuantity(0)
               .build();

        Inventory saved =
                inventoryRepo.save(inventory);
        stockMovementService.createMovement(saved.getProductId(), saved.getAvailableQuantity(), MovementType.STOCK_IN);



        return map(saved);


    }

    @Override
    public InventoryResponse getById(Long id) {
       Inventory inventory = inventoryRepo.findById(id)
               .orElseThrow(()->new InventoryNotFoundException(id));
               return map(inventory);
    }

    @Override
    public List<InventoryResponse> getAll() {
       return inventoryRepo.findAll()
               .stream()
               .map(this::map)
               .toList();
    }

    @Override
    public InventoryResponse update(Long id, UpdateInventoryRequest request) {
        Inventory inventory = inventoryRepo.findById(id)
                .orElseThrow(()->new InventoryNotFoundException(id));

        if(request.availableQuantity() < 0){
            throw  new InvalidQuantityException(request.availableQuantity());
        }
        if(request.reservedQuantity() < 0){
            throw  new InvalidQuantityException(request.availableQuantity());
        }
        inventory.setProductId(request.productId());
        inventory.setAvailableQuantity(request.availableQuantity());
        inventory.setReservedQuantity(request.reservedQuantity());

        return map(inventoryRepo.save(inventory));

    }

    @Override
    public void reserveStock(ReserveStockRequest request) {

        Inventory inventory = inventoryRepo.findByProductId(request.productId())
                .orElseThrow(() ->
                        new InventoryNotFoundException(request.productId()));

        if (inventory.getAvailableQuantity() < request.quantity()) {
            throw new InsufficientStockException(request.productId());
        }
        if(request.quantity() < 0){
            throw  new InvalidQuantityException(request.quantity());
        }


        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - request.quantity());
        inventory.setReservedQuantity(
                inventory.getReservedQuantity() + request.quantity());
        inventoryRepo.save(inventory);
        stockMovementService.createMovement(inventory.getProductId(), request.quantity(),MovementType.RESERVED);


    }

    @Override
    public void releaseStock(ReleaseStockRequest request) {
        Inventory inventory = inventoryRepo.findByProductId(request.productId())
                .orElseThrow(()-> new InventoryNotFoundException(request.productId()));

        if(inventory.getReservedQuantity() < request.quantity()){
            throw new IllegalArgumentException();

        }

        if(request.quantity() < 0){
            throw  new InvalidQuantityException(request.quantity());
        }
        inventory.setReservedQuantity(inventory.getReservedQuantity() - request.quantity());
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + request.quantity());
        inventoryRepo.save(inventory);
        stockMovementService.createMovement(inventory.getProductId(), request.quantity(), MovementType.RELEASED);





    }

    @Override
    public void confirmStock(ConfirmStockRequest request) {

        Inventory inventory = inventoryRepo.findByProductId(request.productId())
                .orElseThrow(() ->new InventoryNotFoundException(request.productId()));

        if(inventory.getReservedQuantity() < request.quantity()){
            throw  new IllegalArgumentException();
        }
        if(request.quantity() < 0){
            throw  new InvalidQuantityException(request.quantity());
        }

        inventory.setReservedQuantity(inventory.getReservedQuantity() - request.quantity());
        inventoryRepo.save(inventory);



        stockMovementService.createMovement(inventory.getProductId(), request.quantity() ,MovementType.CONFIRMED);



    }

    @Override
    public List<StockMovementResponse> getProductId(Long productId) {
        List<StockMovement> movements =
                stockMovementRepo.findByProductId(productId);

        if (movements.isEmpty()) {
            throw new InventoryNotFoundException(productId);
        }

        return movements.stream()
                .map(this::mapping)
                .toList();

    }

    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {

            Inventory inventory = inventoryRepo.findByProductId(productId)
                    .orElseThrow(() -> new InventoryNotFoundException(productId));

            return new InventoryResponse(
                    inventory.getId(),
                    inventory.getProductId(),
                    inventory.getAvailableQuantity(),
                    inventory.getReservedQuantity()
            );

    }


    public void delete(Long id) {
        Inventory inventory = inventoryRepo.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException( id));

        inventoryRepo.delete(inventory);
    }



    private StockMovementResponse mapping(StockMovement stockMovement){
        return new StockMovementResponse(
                stockMovement.getId(),
                stockMovement.getProductId(),
                stockMovement.getQuantity(),
                stockMovement.getMovementType(),
                stockMovement.getCreatedAt()
        );
    }




    private InventoryResponse map(Inventory inventory){
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getAvailableQuantity(),
                inventory.getReservedQuantity()
        );
    }
}
