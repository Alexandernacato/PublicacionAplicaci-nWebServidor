package com.example.prueba.prueba.Services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.prueba.prueba.Client.CategoryClient;
import com.example.prueba.prueba.Client.CategoryDTO;
import com.example.prueba.prueba.Client.ProductResponseDTO;
import com.example.prueba.prueba.Model.Products;
//Spring pueda utlizar esta clase como co un componente paraq que me epermita usar una inyeccion de depsnedencias
import com.example.prueba.prueba.Repository.ProductRepository;



import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductsServiceImplement implements ProductsService {

    private final ProductRepository productRepository;
    private final CategoryClient categoryClient;

    public ProductsServiceImplement(ProductRepository productRepository, CategoryClient categoryClient) {
        this.productRepository = productRepository;
        this.categoryClient = categoryClient;
    }

 @Override
public List<ProductResponseDTO> getAllProducts() {
    Iterable<Products> productsIterable = productRepository.findAll();

    return StreamSupport.stream(productsIterable.spliterator(), false)
        .map(product -> {
            CategoryDTO category = categoryClient.getCategoryById(product.getCategoryid());
            return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryid(),
                category != null ? category.getNombre() : null
            );
        })
        .collect(Collectors.toList());
}

    @Override
    public Optional<ProductResponseDTO> getProductById(Long id) {
        return productRepository.findById(id).map(product -> {
            CategoryDTO category = categoryClient.getCategoryById(product.getCategoryid());
            return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryid(),
                category != null ? category.getNombre() : null
            );
        });
    }

    @Override
    public Products createProduct(Products product) {
        if (product.getCategoryid() == null) {
            throw new RuntimeException("Category ID must not be null");
        }
        CategoryDTO category = categoryClient.getCategoryById(product.getCategoryid());
        if (category == null) {
            throw new RuntimeException("Category not found with ID: " + product.getCategoryid());
        }
        return productRepository.save(product);
    }

    @Override
    public Products updateProduct(Long id, Products product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
}