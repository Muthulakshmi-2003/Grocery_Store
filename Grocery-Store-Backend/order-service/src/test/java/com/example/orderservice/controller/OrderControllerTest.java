package com.example.orderservice.controller;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderService orderService;

    @Test
    void shouldCreateOrder() throws Exception {

        CreateOrderRequest request = new CreateOrderRequest(1L, 1L, 2);

        OrderResponse response = new OrderResponse(
                1L, 1L, 2,
                OrderStatus.CREATED,
                BigDecimal.valueOf(120),
                LocalDateTime.now()
        );

        when(orderService.createOrder(any())).thenReturn(response);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.quantity").value(2));
    }

    @Test
    void shouldReturnOrderById() throws Exception {

        OrderResponse response = new OrderResponse(1L, 3L,4, OrderStatus.CREATED ,BigDecimal.valueOf(60.60), LocalDateTime.now());

        when(orderService.getOrder(1L)).thenReturn(response);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1L))
                .andExpect(jsonPath("$.productId").value(3))
                .andExpect(jsonPath("$.quantity").value(4))
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.totalprice").value(60.6));
    }

    @Test
    void shouldCancelOrder() throws Exception {

        OrderResponse response = new OrderResponse(
                1L, 1L, 2,
                OrderStatus.CANCELLED,
                BigDecimal.valueOf(120),
                LocalDateTime.now()
        );

         doNothing().when(orderService).release(response.orderId());

        mockMvc.perform(put("/orders/1/release"))
                .andExpect(status().isOk())
                .andExpect(content().string("The Order Cancel Successfully!!!"));
    }

    @Test
    void shouldConfirmOrder() throws Exception {

        OrderResponse response = new OrderResponse(
                1L, 1L, 2,
                OrderStatus.CONFIRMED,
                BigDecimal.valueOf(120),
                LocalDateTime.now()
        );

        doNothing().when(orderService).confirmOrder(response.orderId());

        mockMvc.perform(put("/orders/1/confirm"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order Confirmed Successfully!!"));
    }

    @Test
    void shouldDeleteOrder() throws Exception {

        doNothing().when(orderService).delete(1L);

        mockMvc.perform(delete("/orders/1"))
                .andExpect(content().string("Order Deleted Successfully!!"));

        verify(orderService).delete(1L);
    }
}
