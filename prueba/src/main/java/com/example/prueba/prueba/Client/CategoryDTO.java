package com.example.prueba.prueba.Client;

public class CategoryDTO {
    private Long id;
    private String nombre;  // o "name" según tu microservicio de categorías

    public CategoryDTO() {}

    public CategoryDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

}
