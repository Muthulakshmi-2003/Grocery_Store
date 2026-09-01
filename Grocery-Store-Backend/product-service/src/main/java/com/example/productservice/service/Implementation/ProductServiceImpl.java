package com.example.productservice.service.Implementation;

import com.example.productservice.dto.CreateProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.dto.ProductSummaryResponse;
import com.example.productservice.dto.UpdateProductRequest;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.*;
import com.example.productservice.repository.CategoryRepo;
import com.example.productservice.repository.ProductRepo;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;




@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    @Override
    public ProductResponse create(CreateProductRequest request) {
        if(productRepo.existsBySku(request.sku())){
            throw new DuplicateskuException(request.sku());
        }

        if(productRepo.existsByName(request.name())){
            throw new ResponseAlreadyExistException(request.name());

        }

        if(request.price().compareTo(BigDecimal.ZERO) <= 0){
            throw  new InvalidPriceException(request.price());
        }

        categoryRepo.findById(request.categoryId())
                .orElseThrow(()->new CategoryNotFoundException(request.categoryId()));

        Product product = Product.builder()
                .sku(request.sku())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .active(request.active())
                .categoryId(request.categoryId())
                .build();
        return map(productRepo.save(product));
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(()->new ProductNotFoundException(id));
        return map(product);
    }

    @Override
    public List<ProductResponse> getAll() {
       return  productRepo.findAll()
               .stream()
               .map(this::map)
               .toList();
    }

    @Override
    public ProductResponse update(Long id, UpdateProductRequest request) {
        Product product = productRepo.findById(id)
                .orElseThrow(()->new ProductNotFoundException(id ));



        if(request.price().compareTo(BigDecimal.ZERO) <= 0){
            throw  new InvalidPriceException(request.price());
        }
        categoryRepo.findById(request.categoryId())
                .orElseThrow(()->new CategoryNotFoundException(request.categoryId()));

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setActive(request.active());


        return map(productRepo.save(product));

    }

    @Override
    public void delete(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));

        productRepo.delete(product);

    }

    @Override
    public ProductSummaryResponse getProductSummary(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(
                        +id));

        return new ProductSummaryResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getActive()
        );
    }

    public List<Product> searchProducts(String name) {

        return productRepo
                .findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }

    private ProductResponse map(Product prod){
        return new ProductResponse(
                prod.getId(),
                prod.getSku(),
                prod.getName(),
                prod.getDescription(),
                prod.getPrice(),
                prod.getActive(),
                prod.getCategoryId()
        );
    }
}
