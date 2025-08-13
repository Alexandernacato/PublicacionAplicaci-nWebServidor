package com.example.categoria.categoria.Services;

import java.util.List;
import java.util.Optional;

import com.example.categoria.categoria.Model.Categories;

public interface CategoriesService {

    List<Categories> getAllCategories();

    Optional<Categories> getCategoryById(Long id);

    Categories createCategory(Categories category);

    Categories updateCategory(Long id, Categories category);

    void deleteCategory(Long id);
}
