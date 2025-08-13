package com.example.prueba.prueba.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.prueba.prueba.Model.Products;

public interface ProductRepository extends CrudRepository<Products, Long> {
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para buscar productos por nombre:
    // List<Products> findByName(String name);
    

}
