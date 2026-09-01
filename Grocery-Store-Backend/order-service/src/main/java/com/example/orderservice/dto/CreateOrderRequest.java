package com.example.orderservice.dto;


import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest (@NotNull Long customerId,
                                  List<OrderItemRequest> items){
}

