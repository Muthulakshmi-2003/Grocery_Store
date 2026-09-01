package com.example.productservice.controller;

import com.example.productservice.dto.CategoryResponse;
import com.example.productservice.dto.CreateCategoryRequest;
import com.example.productservice.dto.ErrorResponseDto;
import com.example.productservice.dto.UpdateCategoryRequest;
import com.example.productservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "CRUD REST APIs for Online Grocery Store for Category",
        description = "CRUD REST APIs in Grocery Store to CREATE, UPDATE, READ AND DELETE "
)

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(

            summary = "Create Category Details REST API",
            description = "REST API to create new Category"
    )

    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateCategoryRequest.class),
                    examples = @ExampleObject(
                            name = "Category Example",
                            value = """
                                    {
                                      "name": "Dairy",
                                      "description": "Milk and dairy products"
                                    }
                                    """
                    )
            )
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )

    @PostMapping
    public CategoryResponse create(
            @Parameter(description = "User Id", example = "1")
            @Valid @RequestBody CreateCategoryRequest request) {
        return categoryService.create(request);
    }


    @Operation(
            summary = "READ Category with id Details Using a  REST API",
            description = "REST API to  GET a category details using a Id "
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponse.class),
                    examples = @ExampleObject(
                            name = "Category Example",
                            value = """
                                    {
                                     "id": 4,
                                     "name": "Packaged Foods",
                                     "description": "Convenient and tasty ready-to-use food products."
                                    }
                                    """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }


    @Operation(
            summary = "Update Category Details REST API",
            description = "REST API to update Category Details with Id "
    )


    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponse.class),
                    examples = @ExampleObject(
                            name = "Category Example",
                            value = """
                                    {
                                     "name": "Packaged Foods",
                                     "description": "Ready to use methods"
                                    }
                                    """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/{id}")
    public CategoryResponse update(
            @PathVariable Long id,
            @RequestBody UpdateCategoryRequest request) {
        return categoryService.update(id, request);
    }


    @Operation(
            summary = "Get All Categories REST API",
            description = "REST API to fetch all category details"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponse.class),
                    examples = @ExampleObject(
                            name = "Category Example",
                            value = """ 
                                    [
                                    {
                                     "id": 1,
                                     "name": "Fruits",
                                     "description": "Farm Fresh fruits varieties"
                                    },{
                                     "id": 2,
                                     "name": "Vegetables",
                                     "description": "Fresh, healthy vegetables picked for everyday cooking."
                                    },
                                     {
                                     "id": 3,
                                     "name": "Dairy",
                                     "description": "Fresh and nutritious dairy products for your daily needs"
                                    }
                                    ..................
                                    ]
                                    """
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }


    @Operation(
            summary = "Delete Category Details REST API",
            description = "REST API to delete Category Details with Id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Categories deleted successfully.");
    }

}
