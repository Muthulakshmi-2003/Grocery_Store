package com.example.orderservice.service;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

  void release(Long orderId);

    void confirmOrder(Long orderId);

   void  delete(Long id);

    OrderResponse getOrder(Long orderId);
}
