package com.example.orderservice.service.Implementation;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.*;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.exception.*;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImple implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductClient productClient;

    private final InventoryClient inventoryClient;


    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        BigDecimal total = BigDecimal.ZERO;


        Order order = Order.builder()
                .customerId(request.customerId())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest item : request.items()) {

            ProductResponse product;

            try {
                product = productClient.getProductId(item.productId());
            } catch (FeignException.NotFound ex) {
                throw new ProductNotFoundException(item.productId());
            }

            if (!product.active()) {
                throw new ProductInactiveException(item.productId());
            }

            InventoryResponse inventory =
                    inventoryClient.getInventory(item.productId());

            if (inventory.availableQuantity() < item.quantity()) {
                throw new InsufficientStockException(item.productId());
            }

            inventoryClient.reserveStock(
                    new ReserveStockRequest(
                            item.productId(),
                            item.quantity()));

            BigDecimal itemTotal =
                    product.price()
                            .multiply(BigDecimal.valueOf(item.quantity()));

            total = total.add(itemTotal);

            OrderItem orderItem = OrderItem.builder()
                    .productId(item.productId())
                    .quantity(item.quantity())
                    .Unitprice(product.price())
                    .Total(itemTotal)
                    .order(order)
                    .build();

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setOrderTotal(total);

        Order saved = orderRepository.save(order);

        return map(saved);
    }

    @Override
    public void release(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));

        if(order.getStatus() == OrderStatus.DELIVERED){
            throw new InvalidOrderStatusException(orderId);
        }

        if(order.getStatus()==OrderStatus.CONFIRMED){
            throw new ConfirmedOrderCannotCancellException();
        }
        for (OrderItem item : order.getItems()) {

            inventoryClient.releaseStock(
                    new ReleaseStockRequest(
                            item.getProductId(),
                            item.getQuantity()));
        }
    }

    @Override
    public void confirmOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        for (OrderItem item : order.getItems()) {

            inventoryClient.confirmStock(
                    new ConfirmStockRequest(
                            item.getProductId(),
                            item.getQuantity()));
        }

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);

    }

    @Override
    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException(orderId));
        return map(order);

    }

    private OrderResponse map(Order order) {

        List<OrderItemResponse> items =
                order.getItems()
                        .stream()
                        .map(item ->
                                new OrderItemResponse(
                                        item.getProductId(),
                                        item.getQuantity(),
                                        item.getUnitprice()))
                        .toList();

        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerId(),
                order.getStatus(),
                order.getOrderTotal(),
                items
        );
    }

    @Override
    public void delete(Long id) {
        Order order  = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException(id));

        orderRepository.delete(order);

    }

    public List<OrderResponse> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
