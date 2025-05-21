package com.ashen_dissanayake.blog.services.impl;

import com.ashen_dissanayake.blog.domain.entities.Category;
import com.ashen_dissanayake.blog.repositories.CategoryRepository;
import com.ashen_dissanayake.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

   private final CategoryRepository categoryRepository;

   @Override
   public List<Category> getAllCategories() {
      return categoryRepository.findAllWithPostCount();
   }


   @Override
   @Transactional
   public Category createCategory(Category category) {
      if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
         throw new IllegalArgumentException("Category already exists with name: " + category.getName());
      }

      return categoryRepository.save(category);
   }
}
