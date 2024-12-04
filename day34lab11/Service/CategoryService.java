package com.example.day34lab11.Service;

import com.example.day34lab11.ApiResponse.ApiException;
import com.example.day34lab11.Model.Category;
import com.example.day34lab11.Repository.CategoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id, Category category) {
        if (categoryRepository.findCategoriesById(id) == null) throw new ApiException("category not found");
        Category oldCategory = categoryRepository.findCategoriesById(id);
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Integer id) {
        if (categoryRepository.findCategoriesById(id) == null) throw new ApiException("category not found");
        categoryRepository.deleteById(id);
    }

    public Category findCategoryByName(String categoryName) {
        if (categoryRepository.findCategoriesByName(categoryName) == null) throw new ApiException("category not found");
        return categoryRepository.findCategoriesByName(categoryName);
    }
}
