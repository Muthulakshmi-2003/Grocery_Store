package com.example.orderservice.controller;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.ErrorResponseDto;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "CRUD REST APIs for Online Grocery Store for Order-Service",
        description = "CRUD REST APIs in Grocery Store to CREATE, UPDATE, READ AND DELETE "
)


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Create Order  Details REST API",
            description = "REST API to create new Order"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request){
        OrderResponse response = orderService.createOrder(request);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(response);

    }










    @Operation(
            summary = "Release Inventory REST API",
            description = "REST API to release previously reserved inventory"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Inventory released successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Inventory not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/{id}/release")
    public ResponseEntity<String> release (@PathVariable Long id){
        orderService.release(id);
        return ResponseEntity.ok("The Order Cancel Successfully!!!");
    }









    @Operation(
            summary = "Confirm Inventory REST API",
            description = "REST API to confirm inventory and deduct reserved stock"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Inventory confirmed successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Inventory not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Insufficient reserved inventory",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/{id}/confirm")
    public ResponseEntity< String>confirm (@PathVariable Long id){
        orderService.confirmOrder(id);
        return ResponseEntity.ok("Order Confirmed Successfully!!");
    }








    @Operation(
            summary = "GET a Order Details with Id",
            description = "REST API to  GET a Order details using a Id "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }







    @Operation(
            summary = "Delete Order Details REST API",
            description = "REST API to delete Order Details By Id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok("Order Deleted Successfully!!");

    }

    @GetMapping("/customer/{customerId}")
    public List<OrderResponse> getOrdersByCustomer(
            @PathVariable Long customerId) {

        return orderService.getOrdersByCustomer(customerId);
    }


}
