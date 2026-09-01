package com.example.productservice.controller;

import com.example.productservice.dto.CreateProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.dto.UpdateProductRequest;
import com.example.productservice.service.Implementation.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductServiceImpl productService;




    @Test
    void shouldCreateProduct() throws Exception {

        CreateProductRequest request = new CreateProductRequest(
                "APF101",
                "Apple",
                "Healthy Fresh Apple",
                BigDecimal.valueOf(40.60),
                1L,true
        );

        ProductResponse response = new ProductResponse(
                1L,
                "APF101",
                "Apple",
                "Healthy Fresh Apple",
                BigDecimal.valueOf(40.60),
                true,
                1L
        );

        when(productService.create(any(CreateProductRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Apple"));
    }

    @Test
    void shouldGetProduct() throws Exception {

        ProductResponse response = new ProductResponse(
                1L,
                "APF101",
                "Apple",
                "Healthy Fresh Apple",
                BigDecimal.valueOf(40.60),
                true,
                1L);

        when(productService.getById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Apple"));
    }

    @Test
    void shouldGetAllProducts() throws Exception {

        List<ProductResponse> list = List.of(
                new ProductResponse(
                        1L,
                        "APF101",
                        "Apple",
                        "Healthy Fresh Apple",
                        BigDecimal.valueOf(40.60),
                        true,
                        1L)
        );

        when(productService.getAll()).thenReturn(list);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Apple"));
    }

    @Test
    void shouldUpdateProduct() throws Exception {

        UpdateProductRequest request =
                new UpdateProductRequest(
                        "Apple Updated",
                        "Fresh Apple",
                        BigDecimal.valueOf(50),
                        true,
                        "APF101",1L);

        ProductResponse response =
                new ProductResponse(
                        1L,
                        "APF101",
                        "Apple Updated",
                        "Fresh Apple",
                        BigDecimal.valueOf(50),
                        true,
                        1L);

        when(productService.update(eq(1L), any(UpdateProductRequest.class)))
                .thenReturn(response);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Apple Updated"));
    }
    @Test
    void shouldDeleteProduct() throws Exception {

        doNothing().when(productService).delete(1L);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());

        verify(productService).delete(1L);
    }
}
