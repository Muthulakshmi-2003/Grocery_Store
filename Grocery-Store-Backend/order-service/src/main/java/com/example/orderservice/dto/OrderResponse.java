package com.example.orderservice.dto;

import com.example.orderservice.entity.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse (
        Long orderId,
        Long customerId,
        OrderStatus status,
        BigDecimal total,
        List<OrderItemResponse> items) {
}


