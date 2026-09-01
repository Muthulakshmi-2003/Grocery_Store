package com.example.productservice.service;

import com.example.productservice.dto.CreateProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.dto.ProductSummaryResponse;
import com.example.productservice.dto.UpdateProductRequest;
import com.example.productservice.entity.Product;

import java.util.List;

public interface ProductService {

    ProductResponse create(CreateProductRequest request);

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();

    ProductResponse update(Long id, UpdateProductRequest request);

    void delete(Long id);

    ProductSummaryResponse getProductSummary(Long id);

    List <Product> searchProducts(String name);

    List<Product> getProductsByCategory(Long categoryId);
}
