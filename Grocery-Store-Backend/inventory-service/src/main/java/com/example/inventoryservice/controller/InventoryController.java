package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.*;
import com.example.inventoryservice.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Online Grocery Store for Inventory and Stock Movement services",
        description = "CRUD REST APIs in Grocery Store to CREATE, UPDATE, READ AND DELETE "
)


@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService inventoryService;




    @Operation(
            summary = "Create Inventory Details REST API",
            description = "REST API to create new Inventory"
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
    public InventoryResponse create(@Valid @RequestBody CreateInventoryRequest request){
        return inventoryService.create(request);
    }







    @Operation(
            summary = "READ Inventory with id  Using a  REST API",
            description = "REST API to  GET a Inventory details using a Id "
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
    public InventoryResponse getById(@PathVariable Long id){
        return inventoryService.getById(id);
    }







    @Operation(
            summary = "Get All Inventory REST API",
            description = "REST API to fetch all Inventory details"
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

    @GetMapping
    public List<InventoryResponse> getAll(){
        return inventoryService.getAll();
    }






    @Operation(
            summary = "Reserve Inventory REST API",
            description = "REST API to reserve inventory for a product"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Inventory reserved successfully"
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
    @PostMapping("/reserve")
    public ResponseEntity<String> reserveStock(@Valid @RequestBody
                                               ReserveStockRequest request){
        inventoryService.reserveStock(request);
        return ResponseEntity.ok("Stock Reserved Successfully");
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

    @PostMapping("/release")
    public ResponseEntity<String> releaseStock(@Valid @RequestBody
                                               ReleaseStockRequest request){
        inventoryService.releaseStock(request);
        return ResponseEntity.ok("Stock Release Successfully");
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

    @PostMapping("/confirm")
    public ResponseEntity<String> ConfirmStock(@Valid @RequestBody
                                               ConfirmStockRequest request){
        inventoryService.confirmStock(request);
        return ResponseEntity.ok("Stock Confirmed Successfully");
    }









    @Operation(
            summary = "Get Stock Movements by Product ID",
            description = "Retrieves all stock movement records for the specified product."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Stock movements retrieved successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No stock movements found ",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/product/{productId}")
    public List<StockMovementResponse> getProductId(@PathVariable Long productId){
        return inventoryService.getProductId(productId);
    }









    @Operation(
            summary = "Get Inventory by Product ID",
            description = "Retrieves the inventory details for the specified product."
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
            )

    })

    @GetMapping("/product/{productId}/inventory")
    public InventoryResponse getInventoryByProductId(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }


}
