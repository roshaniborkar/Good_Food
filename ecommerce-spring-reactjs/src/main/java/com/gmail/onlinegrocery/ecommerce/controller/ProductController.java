package com.gmail.onlinegrocery.ecommerce.controller;

import java.util.List;

import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductCategoryDto;
import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gmail.onlinegrocery.ecommerce.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

import static com.gmail.onlinegrocery.ecommerce.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PRODUCTS)
public class ProductController {

    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> response = productMapper.getAllProducts();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(PRODUCT_ID)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productMapper.getProductById(productId));
    }

    @GetMapping(AllCategories)
    public ResponseEntity<List<ProductCategoryDto>> getAllCategories() {
        return ResponseEntity.ok(productMapper.getAllCategories());
    }

    @GetMapping(ProductByCategory)
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productMapper.getProductsByCategory(category));
    }


    @PostMapping(ProductByCategories)
    public ResponseEntity<List<ProductDTO>> getProductsByCategories(@RequestBody List<String> categories) {
        List<ProductDTO> result;
        if (categories.isEmpty()) {
            result = productMapper.getAllProducts();
        } else {
            result = productMapper.getProductsByCategories(categories);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping(searchProducts)
    public ResponseEntity<List<ProductDTO>> searchProductsByName(@PathVariable String searchtext) {
        return ResponseEntity.ok(productMapper.searchProductsByName(searchtext));
    }

    @GetMapping(getProductsByTag)
    public ResponseEntity<List<ProductDTO>> getProductsByTag(@PathVariable String productTag) {
        return ResponseEntity.ok(productMapper.getProductsByTag(productTag));
    }

}
