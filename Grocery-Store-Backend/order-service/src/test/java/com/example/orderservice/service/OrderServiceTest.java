package com.example.orderservice.service;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.dto.ProductResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.Implementation.OrderServiceImple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImple orderService;

    @MockitoBean
    private ProductClient productClient;

    @MockitoBean
    private InventoryClient inventoryClient;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateOrderSuccessfully() throws Exception {

        ProductResponse product = new ProductResponse(
                1L, "APF101","Apple",
                "Freshy fruit", BigDecimal.valueOf(60.0),true,1L
        );

        InventoryResponse inventory = new InventoryResponse(
                1L, 1L, 10, 0
        );

        when(productClient.getProductId(1L)).thenReturn(product);
        when(inventoryClient.getInventory(1L)).thenReturn(inventory);

        CreateOrderRequest request = new CreateOrderRequest(1L, 1L, 2);



        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());


    }

    @Test
    void shouldGetOrderById() {

        Order order = new Order();
        order.setId(1L);
        order.setProductId(1L);
        order.setQuantity(2);
        order.setStatus(OrderStatus.CREATED);

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        OrderResponse response = orderService.getOrder(1L);

        assertEquals(1L, response.orderId());
    }


    @Test
    void shouldConfirmOrder() {

        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.CREATED);

        Order confirmed = new Order();
        confirmed.setId(1L);
        confirmed.setStatus(OrderStatus.CONFIRMED);

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(confirmed);

         orderService.confirmOrder(1L);

        assertEquals(OrderStatus.CONFIRMED, confirmed.getStatus());
    }

    @Test
    void shouldCancelOrder() {

        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.CREATED);

        Order cancelled = new Order();
        cancelled.setId(1L);
        cancelled.setStatus(OrderStatus.CANCELLED);

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(cancelled);

       orderService.release(1L);

        assertEquals(OrderStatus.CANCELLED, cancelled.getStatus());
    }


}
