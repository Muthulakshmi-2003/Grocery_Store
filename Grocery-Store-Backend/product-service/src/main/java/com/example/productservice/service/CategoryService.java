package com.example.productservice.service;

import com.example.productservice.dto.CategoryResponse;
import com.example.productservice.dto.CreateCategoryRequest;
import com.example.productservice.dto.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CreateCategoryRequest request);

    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id);

    CategoryResponse update(Long id , UpdateCategoryRequest request);

    void delete(Long id);


}
