package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepoTest {
    @Autowired
  private  ProductRepo productRepo;


    @Test
    void shouldReturnTrueIfProductNameExists() {

        Product product = new Product();

        product.setName("Apple");
        product.setSku("APF101");
        product.setDescription("Freshy Farm Fruit");
        product.setActive(true);
        product.setCategoryId(1L);
        product.setPrice(BigDecimal.valueOf(1000));

        productRepo.save(product);

        boolean exists = productRepo.existsByName("Apple");

        assertTrue(exists);
    }

    @Test
    void shouldReturnFalseIfSkuDoesNotExist() {

        boolean exists = productRepo.existsBySku("Invalid sku");

        assertFalse(exists);
    }
}
