package com.example.categoria.categoria.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.categoria.categoria.Model.Categories;
import com.example.categoria.categoria.Services.CategoriesService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*") 
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<Categories> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        Optional<Categories> category = categoriesService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categories createCategory(@RequestBody Categories category) {
        return categoriesService.createCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable Long id, @RequestBody Categories category) {
        try {
            Categories updated = categoriesService.updateCategory(id, category);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoriesService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
