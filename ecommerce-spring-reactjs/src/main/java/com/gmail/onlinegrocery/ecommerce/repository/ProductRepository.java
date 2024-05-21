package com.gmail.onlinegrocery.ecommerce.repository;

import com.gmail.onlinegrocery.ecommerce.domain.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByOrderByIdAsc();

    List<Product> findTop10ByProductNameStartingWithIgnoreCaseOrderByPriceDesc(String productName);
    List<Product> findTop10ByProductTagIgnoreCaseOrderByPriceDesc(String productTag);
    List<Product> findByIdIn(List<Integer> perfumesIds);
}
