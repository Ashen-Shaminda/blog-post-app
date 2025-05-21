package com.ashen_dissanayake.blog.services;

import com.ashen_dissanayake.blog.domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
   List<Category> getAllCategories();

   Category createCategory(Category category);

   void deleteCategory(UUID id);
}
