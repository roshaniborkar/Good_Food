package com.gmail.onlinegrocery.ecommerce.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.gmail.onlinegrocery.ecommerce.domain.Product;
import com.gmail.onlinegrocery.ecommerce.domain.ProductCategory;
import com.gmail.onlinegrocery.ecommerce.exception.ApiRequestException;
import com.gmail.onlinegrocery.ecommerce.repository.ProductRepository;
import com.gmail.onlinegrocery.ecommerce.repository.ProductCategoryRepository;
import com.gmail.onlinegrocery.ecommerce.service.ProductService;
import com.gmail.onlinegrocery.ecommerce.constants.ErrorMessage;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final AmazonS3 amazonS3client;

    @Value("${amazon.s3.bucket.name}")
    private String bucketName;

    @Override
    public Product getPerfumeById(Integer productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ApiRequestException(ErrorMessage.PERFUME_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Product> searchproductUsingName(String name) {
        return productRepository.findTop10ByProductNameStartingWithIgnoreCaseOrderByPriceDesc(name);
    }

    @Override
    public List<Product> getproductByTag(String tag) {
        return  productRepository.findTop10ByProductTagIgnoreCaseOrderByPriceDesc(tag);
    }

    //
    @Override
    public List<Product> getPerfumesByIds(List<Integer> perfumesId) {
        return productRepository.findAllById(perfumesId);
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAllCategoriesWithoutProducts();
    }

    @Override
    public Set<Product> getProductsByCategory(Integer categoryId) {
        ProductCategory category = productCategoryRepository.findById(categoryId).get();
        return category.getProducts();
    }

    @Override
    public List<Product> getProductsByCategories(List<Integer> categoryIds) {
        List<Product> products =  productRepository.findAll();
        List<ProductCategory> categories = productCategoryRepository.findByIdIn(categoryIds);
        List<Product> allData = categories.stream()
                .flatMap(obj -> obj.getProducts().stream())
                .collect(Collectors.toList());
        return allData;
    }

    @Override
    public ProductCategory getCategoryById(Integer categoryId) {
        ProductCategory category = productCategoryRepository.findById(categoryId).get();
        return category;
    }

    @Override
    @Transactional
    public Product saveProduct(Product perfume) {
        return productRepository.save(perfume);
    }

    @Override
    @Transactional
    public String deletePerfume(Integer perfumeId) {
        Product perfume = productRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException(ErrorMessage.PERFUME_NOT_FOUND, HttpStatus.NOT_FOUND));
        productRepository.delete(perfume);
        return "Perfume deleted successfully";
    }

    @Override
    public DataFetcher<Product> getPerfumeByQuery() {
        return dataFetchingEnvironment -> {
            Integer perfumeId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
            return productRepository.findById(perfumeId).get();
        };
    }

    @Override
    public DataFetcher<List<Product>> getAllPerfumesByQuery() {
        return dataFetchingEnvironment -> productRepository.findAllByOrderByIdAsc();
    }

    @Override
    public void saveProductCategory(ProductCategory category) {
        productCategoryRepository.save(category);
    }

    @Override
    public DataFetcher<List<Product>> getAllPerfumesByIdsQuery() {
        return dataFetchingEnvironment -> {
            List<String> objects = dataFetchingEnvironment.getArgument("ids");
            List<Integer> perfumesId = objects.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return productRepository.findByIdIn(perfumesId);
        };
    }
}
