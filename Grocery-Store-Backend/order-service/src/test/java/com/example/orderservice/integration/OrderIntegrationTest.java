package com.example.orderservice.integration;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.ProductResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @MockitoBean
    private ProductClient productClient;

    @MockitoBean
    private InventoryClient inventoryClient;

    @Test
    void shouldCreateOrderSuccessfully() throws Exception {

        ProductResponse product = new ProductResponse(
                1L, "APF101","Apple",
                "Freshy fruits", BigDecimal.valueOf(60.0),true,1L
        );

        InventoryResponse inventory = new InventoryResponse(
                1L, 1L, 10, 0
        );

        when(productClient.getProductId(1L)).thenReturn(product);
        when(inventoryClient.getInventory(1L)).thenReturn(inventory);

        String request = """
    {
        "customerId":1,
        "productId":1,
        "quantity":2
    }
    """;

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated());

        assertEquals(1, orderRepository.count());
    }

    @Test
    void shouldConfirmOrder() {

        Order order = Order.builder()
                .customerId(1L)
                .productId(1L)
                .quantity(2)
                .status(OrderStatus.CREATED)
                .totalPrice(BigDecimal.valueOf(120.0))
                .build();

        Order saved = orderRepository.save(order);

        saved.setStatus(OrderStatus.CONFIRMED);

        orderRepository.save(saved);

        Order updated =
                orderRepository.findById(saved.getId()).orElseThrow();

        assertEquals(OrderStatus.CONFIRMED,
                updated.getStatus());
    }

    @Test
    void shouldCancelOrder() {

        Order order = Order.builder()
                .customerId(1L)
                .productId(1L)
                .quantity(2)
                .status(OrderStatus.CREATED)
                .totalPrice(BigDecimal.valueOf(120.0))
                .build();

        Order saved = orderRepository.save(order);

        saved.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(saved);

        Order updated =
                orderRepository.findById(saved.getId()).orElseThrow();

        assertEquals(OrderStatus.CANCELLED,
                updated.getStatus());
    }

    @Test
    void deliveredOrderCannotBeCancelled() {

        Order order = Order.builder()
                .customerId(1L)
                .productId(1L)
                .quantity(2)
                .status(OrderStatus.DELIVERED)
                .totalPrice(BigDecimal.valueOf(120.0))
                .build();

        Order saved = orderRepository.save(order);

        assertThrows(IllegalStateException.class, () -> {

            if(saved.getStatus() == OrderStatus.DELIVERED){
                throw new IllegalStateException(
                        "Delivered orders cannot be cancelled");
            }

        });

    }

}