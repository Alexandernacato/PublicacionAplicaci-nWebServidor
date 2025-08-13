package com.example.prueba.prueba.Model;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Products {

    @Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank
private String name;
@NotBlank
private String description;
@NonNull 
@DecimalMin(value = "0.01")
private Double price;

//Comunicicion directa con categoria utlizaremos feint 
private Long categoryid;


public Products() {
}
public Products(Long id, String name, String description, Double price, Long categoryid) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;     
    this.categoryid = categoryid;
}
// Getters and Setters
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.
    name = name;
}   
public Long getCategoryid() {
    return categoryid;
}
public String getDescription() {
    return description;
}   
public void setDescription(String description) {
    this.description = description;
}
public Double getPrice() {
    return price;
}
public void setPrice(Double price) {
    this.price = price;
}
public void setCategoryid(Long categoryid) {
    this.categoryid = categoryid;
}
//has metodos 


}
