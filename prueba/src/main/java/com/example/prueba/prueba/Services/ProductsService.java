package com.example.prueba.prueba.Services;

import java.util.List;
import java.util.Optional;

import com.example.prueba.prueba.Client.ProductResponseDTO;
import com.example.prueba.prueba.Model.Products;
public interface ProductsService {

   List<ProductResponseDTO> getAllProducts();
    Optional<ProductResponseDTO> getProductById(Long id);

    Products createProduct(Products product);
    Products updateProduct(Long id, Products product);
    void deleteProduct(Long id);
    

}
