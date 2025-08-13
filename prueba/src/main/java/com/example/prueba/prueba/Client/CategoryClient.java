package com.example.prueba.prueba.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category", url = "http://categoria:8084/api/categories")
public interface CategoryClient {
    @GetMapping("/{id}")
    CategoryDTO getCategoryById(@PathVariable("id") Long id);
}
