package com.example.categoria.categoria.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.categoria.categoria.Model.Categories;
import com.example.categoria.categoria.Repository.CategoriesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriesServiceImplement implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public CategoriesServiceImplement(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<Categories> getAllCategories() {
        return (List<Categories>) categoriesRepository.findAll();
    }

    @Override
    public Optional<Categories> getCategoryById(Long id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public Categories createCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    @Override
    public Categories updateCategory(Long id, Categories category) {
        if (categoriesRepository.existsById(id)) {
            category.setId(id);
            return categoriesRepository.save(category);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoriesRepository.existsById(id)) {
            categoriesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }
}
