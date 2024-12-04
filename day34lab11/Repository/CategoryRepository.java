package com.example.day34lab11.Repository;

import com.example.day34lab11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoriesById(Integer id);

    Category findCategoriesByName(String name);


}
