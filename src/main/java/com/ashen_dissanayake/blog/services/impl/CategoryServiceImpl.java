package com.ashen_dissanayake.blog.services.impl;

import com.ashen_dissanayake.blog.domain.entities.Category;
import com.ashen_dissanayake.blog.repositories.CategoryRepository;
import com.ashen_dissanayake.blog.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

   @Override
   public void deleteCategory(UUID id) {
      Category category = getCategoryById(id);

      if (!category.getPosts().isEmpty()) {
         throw new IllegalStateException(
                 "Cannot delete category: " + category.getName() + ". It has associated posts.");
      }
      categoryRepository.delete(category);
   }


   @Override
   public Category getCategoryById(UUID id) {
      return categoryRepository
              .findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Category not found with id" + id));
   }
}
