package com.example.prueba.prueba.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.prueba.Client.ProductResponseDTO;
import com.example.prueba.prueba.Model.Products;

import com.example.prueba.prueba.Services.ProductsService;

import jakarta.validation.Valid;

@RestController
//Ruta para que el usuario reciba las solicitudes 

@CrossOrigin(origins = "http://localhost:8080") 
//Permite que el cliente de Angular pueda hacer solicitudes a este controlador
@RequestMapping("/api/products") 
//Explica el rquestMapping

public class ProductController {
    //No va recibir los metodos HTTp hasta que o registremos como un controller 
    //Utliza HTTp para recibir las soclucitudes del usuario 
    //Los jsp solo recibian dos metodo get y put 
    //Crear y consultar 


    private final ProductsService productsService;

    public ProductController(ProductsService productsService){
        this.productsService = productsService; 
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts(){
        return productsService.getAllProducts();
    }
    

    @GetMapping("/{id}")

    public ResponseEntity<?> getProductsById(@PathVariable Long id){
        return productsService.getProductById(id).map(ResponseEntity:: ok).
        orElse(ResponseEntity.notFound().build());
    }

    @PostMapping 
    public ResponseEntity<?> createProduct(@Valid @RequestBody Products products){
        return ResponseEntity.status(HttpStatus.CREATED).
        body(productsService.createProduct(products));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @PathVariable Long id, @RequestBody Products products){
        try {
            return ResponseEntity.ok(productsService.updateProduct(id, products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try {
            productsService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
