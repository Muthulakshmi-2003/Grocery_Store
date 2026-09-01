package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.service.StockMovementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest

class InventoryRepositoryTest {


    @Autowired
    private InventoryRepo inventoryRepo;

    @MockitoBean
    private StockMovementService stockMovementService;



    @Test
    void shouldSaveAndFindInventory() {

        Inventory inv = new Inventory();

        inv.setProductId(1L);
        inv.setAvailableQuantity(50);
        inv.setReservedQuantity(20);


        inventoryRepo.save(inv);

        Optional<Inventory> result = inventoryRepo.findByProductId(1L);

        assertTrue(result.isPresent());
    }
}
