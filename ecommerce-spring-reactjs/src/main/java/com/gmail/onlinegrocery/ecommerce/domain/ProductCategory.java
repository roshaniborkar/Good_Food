package com.gmail.onlinegrocery.ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "productcategory")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "categoryname", nullable = false, length = 255)
    private String categoryName;

    @Column(name = "imagepath", length = 255)
    private String imagePath;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    // Constructor for JPQL queries
    public ProductCategory(int id, String categoryName, String imagePath) {
        this.id = id;
        this.categoryName = categoryName;
        this.imagePath = imagePath;
    }

    // Default constructor for JPA
    public ProductCategory() {
    }
}
