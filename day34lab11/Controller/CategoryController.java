package com.example.day34lab11.Controller;

import com.example.day34lab11.ApiResponse.ApiResponse;
import com.example.day34lab11.Model.Category;
import com.example.day34lab11.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("category added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.updateCategory(id, category);
        return ResponseEntity.status(200).body(new ApiResponse("category updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("category deleted successfully"));
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity findCategoryByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(categoryService.findCategoryByName(name));
    }


}
