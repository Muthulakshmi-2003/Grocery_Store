package com.example.productservice.dto;

import java.math.BigDecimal;

public record ProductResponse(Long id, String sku, String name, String description, BigDecimal price,
                             Boolean active , Long categoryId) {

}
