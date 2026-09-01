package com.example.orderservice.repository;

import com.example.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo  extends JpaRepository<OrderItem , Long> {
    
}
