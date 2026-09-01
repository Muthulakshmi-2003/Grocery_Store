package com.example.productservice.service.Implementation;

import com.example.productservice.dto.CategoryResponse;
import com.example.productservice.dto.CreateCategoryRequest;
import com.example.productservice.dto.UpdateCategoryRequest;
import com.example.productservice.entity.Category;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.repository.CategoryRepo;
import com.example.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;


    @Override
    public CategoryResponse create(CreateCategoryRequest request) {

        
        Category category = Category.builder()
                .name(request.name())
                .description(request.description())
                .imageUrl(request.image_url())
                .build();
      return mapping(categoryRepo.save(category));
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepo.findAll()
                .stream()
                .map(this::mapping)
                .toList();
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()->new CategoryNotFoundException(id));
        return mapping(category);
    }

    @Override
    public CategoryResponse update(Long id, UpdateCategoryRequest request) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));


       category.setName(request.name());
       category.setDescription(request.description());
       category.setImageUrl(request.image_url());
       return  mapping(categoryRepo.save(category));
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()->new CategoryNotFoundException(id));

        categoryRepo.delete(category);

    }

    private CategoryResponse mapping(Category cate){
        return new CategoryResponse(
            cate.getId(),
            cate.getName(),
                cate.getDescription(),
                cate.getImageUrl()
        );
    }
}
