package com.gmail.onlinegrocery.ecommerce.dto.Product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductDTO  {
    private Integer productId;
    private String ProductName;
    private String ProductTag;
    private String imagePath;
    private int price;
    private String productCategory;
    private String description;
    private String seller;
    private Integer rating;
    private String review;
}
