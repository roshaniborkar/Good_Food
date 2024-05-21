package com.gmail.onlinegrocery.ecommerce.repository;

import com.gmail.onlinegrocery.ecommerce.domain.Product;
import com.gmail.onlinegrocery.ecommerce.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>
{
    @Query("SELECT new ProductCategory(pc.id, pc.categoryName, pc.imagePath) FROM ProductCategory pc")
    List<ProductCategory> findAllCategoriesWithoutProducts();

    List<ProductCategory> findByIdIn(List<Integer> ids);

    // Fetch a single category by ID without loading related products
}
