package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    boolean existsByName(String name);

    boolean existsBySku(String sku);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryId(Long categoryId);




}
