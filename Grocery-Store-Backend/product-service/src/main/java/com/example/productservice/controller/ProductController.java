package com.example.productservice.controller;

import com.example.productservice.dto.*;
import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
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
        name = "CRUD REST APIs for Online Grocery Store for Product",
        description = "CRUD REST APIs in Grocery Store to CREATE, UPDATE, READ AND DELETE "
)

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor 
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Create Product Details REST API",
            description = "REST API to create new Product"
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
    public ProductResponse create(@Valid @RequestBody CreateProductRequest request){
        return productService.create(request);
    }






    @Operation(
            summary = "READ Product with id Details Using a  REST API",
            description = "REST API to  GET a Product details using a Id "
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
    public  ResponseEntity<ProductResponse> getById(@PathVariable Long id){
        System.out.println("Received Product ID: " + id);
        return ResponseEntity.ok(productService.getById(id));
    }







    @Operation(
            summary = "Get All Product REST API",
            description = "REST API to fetch all Product details"
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
    public List<ProductResponse> getAll(){
        return productService.getAll();
    }





    @Operation(
            summary = "Update Product Details REST API",
            description = "REST API to update Product Details with Id "
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
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id,
                                @Valid  @RequestBody UpdateProductRequest request){
        return productService.update(id,request);
    }







    @Operation(
            summary = "Delete Product Details REST API",
            description = "REST API to delete Product Details with Id"
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
    public void delete(@PathVariable Long id){
         productService.delete(id);
    }






    @Operation(
            summary = "Fetch  Product order Summary Details REST API",
            description = "REST API to Fetch  Product order Summary Details with Id"
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
    @GetMapping("/{id}/summary")
    public ResponseEntity<ProductSummaryResponse> getProductSummary(@PathVariable Long id){
        return ResponseEntity.ok(
                productService.getProductSummary(id)
        );
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String name) {

        return productService.searchProducts(name);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(
            @PathVariable Long categoryId) {

        return productService.getProductsByCategory(categoryId);
    }












}
