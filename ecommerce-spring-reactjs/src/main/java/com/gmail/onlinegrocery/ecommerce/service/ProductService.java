package com.gmail.onlinegrocery.ecommerce.service;

import com.gmail.onlinegrocery.ecommerce.domain.Product;
import com.gmail.onlinegrocery.ecommerce.domain.ProductCategory;
import graphql.schema.DataFetcher;

import java.util.List;
import java.util.Set;

public interface ProductService {

    Product getPerfumeById(Integer perfumeId);

    List<Product> getAllProducts();
    List<Product> searchproductUsingName(String name);
    List<Product> getproductByTag(String tag);

    List<ProductCategory> getAllProductCategories();
    Set<Product> getProductsByCategory(Integer categoryId);
    List<Product>  getProductsByCategories(List<Integer> categoryIds);
    ProductCategory getCategoryById(Integer categoryId);

    List<Product> getPerfumesByIds(List<Integer> perfumesId);
//
//    Page<PerfumeProjection> findPerfumesByFilterParams(PerfumeSearchRequest filter, Pageable pageable);
//
//    List<Product> findByPerfumer(String perfumer);
//
//    List<Product> findByPerfumeGender(String perfumeGender);
//
//    Page<PerfumeProjection> findByInputText(SearchPerfume searchType, String text, Pageable pageable);

    Product saveProduct(Product perfume);

    String deletePerfume(Integer perfumeId);

    DataFetcher<Product> getPerfumeByQuery();

    DataFetcher<List<Product>> getAllPerfumesByQuery();

    DataFetcher<List<Product>> getAllPerfumesByIdsQuery();

    void saveProductCategory(ProductCategory category);
}
