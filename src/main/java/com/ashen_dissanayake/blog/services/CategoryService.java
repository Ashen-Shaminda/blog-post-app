package com.ashen_dissanayake.blog.services;

import com.ashen_dissanayake.blog.domain.entities.Category;

import java.util.List;

public interface CategoryService {
   List<Category> getAllCategories();

Category createCategory(Category category);
}
