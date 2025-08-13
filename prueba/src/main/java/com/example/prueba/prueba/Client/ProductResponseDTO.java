package com.example.prueba.prueba.Client;

public class ProductResponseDTO {
private Long id;
    private String name;
    private String description;
    private Double price;
    private Long categoryid;
    private String categoryName;

    public ProductResponseDTO(Long id, String name, String description, Double price, Long categoryid, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryid = categoryid;
        this.categoryName = categoryName;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Long getCategoryid() { return categoryid; }
    public void setCategoryid(Long categoryid) { this.categoryid = categoryid; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
