package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.CreateInventoryRequest;
import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.repository.InventoryRepo;
import com.example.inventoryservice.service.InventoryService;
import com.example.inventoryservice.service.StockMovementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventoryService inventoryService;

    @MockitoBean
    private InventoryRepo inventoryRepo;

    @MockitoBean
    private StockMovementService stockMovementService;

    @Test
    void shouldCreateInventory() throws Exception {

        InventoryResponse response =
                new InventoryResponse(1L,1L,10,0);

        when(inventoryService.create(any(CreateInventoryRequest.class)))
                .thenReturn(response);

        String json = """
    {
      "productId":1,
      "availableQuantity":10
    }
    """;

        mockMvc.perform(post("/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.availableQuantity").value(10));
    }

    @Test
    void shouldReturnInventoryById() throws Exception{
        InventoryResponse response = new InventoryResponse(1L,1L,5,2);
        when(inventoryService.getById(1L))
                .thenReturn(response);
        mockMvc.perform(get("/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.availableQuantity").value(5));
    }

    @Test
    void shouldReserveStock() throws Exception {

        doNothing().when(inventoryService)
                .reserveStock(any());

        String json = """
    {
      "productId":1,
      "quantity":5
    }
    """;

        mockMvc.perform(post("/inventory/reserve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReleaseStock() throws Exception {

        doNothing().when(inventoryService)
                .releaseStock(any());

        String json = """
    {
      "productId":1,
      "quantity":5
    }
    """;

        mockMvc.perform(post("/inventory/release")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(inventoryService).releaseStock(any());
    }

    @Test
    void shouldConfirmStock() throws Exception {

        doNothing().when(inventoryService)
                .confirmStock(any());

        String json = """
    {
      "productId":1,
      "quantity":5
    }
    """;

        mockMvc.perform(post("/inventory/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(inventoryService).confirmStock(any());
    }




}

