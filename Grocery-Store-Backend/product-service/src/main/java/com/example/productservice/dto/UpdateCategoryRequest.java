package com.example.productservice.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(@NotBlank String name , String description , String image_url ) {
}
