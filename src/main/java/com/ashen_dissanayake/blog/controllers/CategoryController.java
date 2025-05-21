package com.ashen_dissanayake.blog.controllers;

import com.ashen_dissanayake.blog.domain.dtos.CategoryDto;
import com.ashen_dissanayake.blog.domain.dtos.CreateCategoryRequest;
import com.ashen_dissanayake.blog.domain.entities.Category;
import com.ashen_dissanayake.blog.mappers.CategoryMapper;
import com.ashen_dissanayake.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

   private final CategoryService categoryService;
   private final CategoryMapper categoryMapper;

   @GetMapping
   public ResponseEntity<List<CategoryDto>> getAllCategories() {
      List<CategoryDto> categories = categoryService.getAllCategories()
              .stream()
              .map(categoryMapper::toDto)
              .toList();

      return ResponseEntity.ok(categories);
   }

   @PostMapping
   public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
      Category category = categoryMapper.toEntity(createCategoryRequest);
      Category savedCategory = categoryService.createCategory(category);

      return new ResponseEntity<>(categoryMapper.toDto(savedCategory), HttpStatus.CREATED);
   }
}
