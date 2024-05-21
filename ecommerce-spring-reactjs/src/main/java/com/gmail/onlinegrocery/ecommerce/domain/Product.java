package com.gmail.onlinegrocery.ecommerce.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@ToString
@Entity
@Table(name = "Product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "productname", length = 255)
    private String productName;

    @Column(name = "imagepath", length = 255)
    private String imagePath;

    @Column(name = "producttag", length = 100)
    private String productTag;

    @Column(name = "seller", length = 100)
    private String seller;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "review")
    private String  review;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid", nullable = false)
    private ProductCategory category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product perfume = (Product) o;
        return Objects.equals(id, perfume.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
