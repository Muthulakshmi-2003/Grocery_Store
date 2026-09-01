package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.CreateInventoryRequest;
import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.dto.UpdateInventoryRequest;
import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepo;
import com.example.inventoryservice.service.Implementataion.InventoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepo inventoryRepo;

    @Mock
    private StockMovementService stockMovementService;

    @InjectMocks
    private InventoryServiceImpl inventoryService;




    @Test
    void shouldCreateInventory() {

        CreateInventoryRequest request =
                new CreateInventoryRequest(1L, 10);

        Inventory savedInventory = Inventory.builder()
                .id(1L)
                .productId(1L)
                .availableQuantity(10)
                .reservedQuantity(0)
                .build();

        Mockito.when(inventoryRepo.save(Mockito.any(Inventory.class)))
                .thenReturn(savedInventory);


        Mockito.doNothing()
                .when(stockMovementService)
                .createMovement(Mockito.anyLong(), Mockito.anyInt(), Mockito.any());

        InventoryResponse response = inventoryService.create(request);

        assertNotNull(response);
        assertEquals(10, response.availableQuantity());

        Mockito.verify(inventoryRepo).save(Mockito.any(Inventory.class));
    }


    @Test
    void shouldGetStockByProductId(){
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setProductId(1L);
        inventory.setAvailableQuantity(10);
        inventory.setReservedQuantity(35);

        Mockito.when(inventoryRepo.findByProductId(1L))
                .thenReturn(Optional.of(inventory));

        InventoryResponse response = inventoryService.getInventoryByProductId(1L);

        assertNotNull(response);
        assertEquals(10, response.availableQuantity());

    }

    @Test
    void shouldUpdateInventory() {

        Inventory existing = new Inventory();
        existing.setId(1L);
        existing.setProductId(1L);
        existing.setAvailableQuantity(10);

        UpdateInventoryRequest updated = new UpdateInventoryRequest(1L,20,40);


        Mockito.when(inventoryRepo.findById(1L))
                .thenReturn(Optional.of(existing));

        Mockito.when(inventoryRepo.save(Mockito.any(Inventory.class)))
                .thenReturn(existing);

        InventoryResponse result = inventoryService.update(1L, updated);

        assertEquals(20, result.availableQuantity());
    }

    @Test
    void shouldDeleteInventory() {

        Inventory inventory = new Inventory();
        inventory.setId(1L);

        Mockito.when(inventoryRepo.findById(1L))
                .thenReturn(Optional.of(inventory));

        inventoryService.delete(1L);

        Mockito.verify(inventoryRepo, Mockito.times(1))
                .delete(inventory);
    }

    @Test
    void shouldThrowWhenInventoryNotFound() {

        Mockito.when(inventoryRepo.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> inventoryService.delete(1L));
    }


}
