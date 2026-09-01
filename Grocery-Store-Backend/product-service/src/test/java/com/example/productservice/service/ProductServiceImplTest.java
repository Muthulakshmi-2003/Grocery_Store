package com.example.productservice.service;

import com.example.productservice.dto.CreateProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.dto.UpdateProductRequest;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.DuplicateskuException;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.repository.CategoryRepo;
import com.example.productservice.repository.ProductRepo;
import com.example.productservice.service.Implementation.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ProductServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void shouldCreateProduct() {

        CreateProductRequest request = new CreateProductRequest(
                "APF101",
                "Apple",
                "Healthy Fresh Apple",
                BigDecimal.valueOf(40.60),
                1L,true
        );

        Category category = Category.builder()
                .id(1L)
                .name("Fruits")
                .build();

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Apple")
                .sku("APF101")
                .price(BigDecimal.valueOf(40.60))
                .active(true)
                .categoryId(1L)
                .build();

        when(productRepo.existsBySku("APF101")).thenReturn(false);
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
        when(productRepo.save(any(Product.class))).thenReturn(savedProduct);

        ProductResponse response = productService.create(request);

        assertNotNull(response);
        assertEquals("Apple", response.name());

        verify(productRepo).save(any(Product.class));
    }



    @Test
    void GetProductById() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Rice");

        Mockito.when(productRepo.findById(1L))
                .thenReturn(Optional.of(product));

        ProductResponse response = productService.getById(1L);

        assertEquals("Rice", response.name());
    }

    @Test
    void shouldUpdateProduct() {

        Product existing = Product.builder()
                .id(1L)
                .name("Apple")
                .sku("APF101")
                .price(BigDecimal.valueOf(60.40))
                .build();

        UpdateProductRequest request = new UpdateProductRequest(
                "Apple",
                "Freshy Fruit apple",
                BigDecimal.valueOf(60.90),
                true,
                "APF101",
                1L
        );

        Category category = Category.builder()
                .id(1L)
                .name("Fruits")
                .build();

        Product updated = Product.builder()
                .id(1L)
                .name("Apple")
                .sku("APF101")
                .price(BigDecimal.valueOf(60.90))
                .build();

        when(productRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
        when(productRepo.save(any(Product.class))).thenReturn(updated);

        ProductResponse response = productService.update(1L,request);

        assertEquals("Apple", response.name());
        assertEquals(BigDecimal.valueOf(60.90), response.price());
    }

    @Test
    void shouldDeleteProduct() {

        Product product = Product.builder()
                .id(1L)
                .name("Apple")
                .build();

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        productService.delete(1L);

        verify(productRepo).delete(product);
    }

    @Test
    void shouldGetAllProducts() {

        List<Product> products = List.of(
                Product.builder().id(1L).name("Apple").build(),
                Product.builder().id(2L).name("Rice").build()
        );

        when(productRepo.findAll()).thenReturn(products);

        List<ProductResponse> response = productService.getAll();

        assertEquals(2, response.size());
    }


    @Test
    void shouldThrowProductNotFound() {

        when(productRepo.findById(100L)).thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.getById(100L)
        );
    }

    @Test
    void shouldThrowDuplicateSku() {

        CreateProductRequest request = new CreateProductRequest(
                "APF101",
                "Apple",
                "Healthy Fresh Apple",
                BigDecimal.valueOf(40.60),
                1L,true
        );

        when(productRepo.existsBySku("APF101")).thenReturn(true);

        assertThrows(
                DuplicateskuException.class,
                () -> productService.create(request)
        );
    }



}

