package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.ErrorResponseDto;
import com.example.inventoryservice.dto.StockMovementResponse;
import com.example.inventoryservice.service.StockMovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Stock Movement Management",
        description = "APIs for managing and retrieving stock movement records."
)

@RestController
@RequestMapping("/stock-movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;


    @Operation(
            summary = "Get All Stock Movements",
            description = "Retrieves all stock movement records ."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Stock movements retrieved successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "No stock movements found",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @GetMapping
    public List<StockMovementResponse> getAll() {
        return stockMovementService.getAll();

    }



    @Operation(
            summary = "Get All Stock Movements by Product Id",
            description = "Retrieves all stock movement records for the specified product ."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Stock movements retrieved successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "No stock movements found for the specified product ",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
            )


    @GetMapping("/product/{productId}")
    public List< StockMovementResponse> getByProductId(
            @PathVariable Long productId){
        return stockMovementService.getByProductId(productId);
    }

}
