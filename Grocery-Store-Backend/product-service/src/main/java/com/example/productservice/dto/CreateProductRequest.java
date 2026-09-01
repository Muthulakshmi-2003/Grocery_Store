package com.example.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank String sku, @NotBlank String name , String description,
        @Positive @NotNull BigDecimal price , @NotNull  Long categoryId,@NotNull (message = "Active is required") Boolean active
        ) {
}
