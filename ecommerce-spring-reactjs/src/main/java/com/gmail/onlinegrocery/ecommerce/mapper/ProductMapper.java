package com.gmail.onlinegrocery.ecommerce.mapper;

import com.amazonaws.services.route53.model.InvalidInputException;
import com.gmail.onlinegrocery.ecommerce.domain.Product;
import com.gmail.onlinegrocery.ecommerce.domain.ProductCategory;
import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductCategoryDto;
import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductDTO;
import com.gmail.onlinegrocery.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CommonMapper commonMapper;
    private final ProductService productService;
    @Value("${imageProcesorBaseUrl}")
    private String imageProcessingServerUrl;;
    private static Map<String, Integer> categoriesMapping = initializeCategories();

    public ProductDTO getProductById(Integer perfumeId) {
        Product product = productService.getPerfumeById(perfumeId);

        return mapToProductDto(product);
    }
    //
    // public List<PerfumeResponse> getPerfumesByIds(List<Long> perfumesId) {
    // return
    // commonMapper.convertToResponseList(perfumeService.getPerfumesByIds(perfumesId),
    // PerfumeResponse.class);
    // }

    public List<ProductCategoryDto> getAllCategories() {
        List<ProductCategory> productCategories = productService.getAllProductCategories();
        List<ProductCategoryDto> result = new ArrayList<ProductCategoryDto>();
        for (ProductCategory productCategory : productCategories) {
            result.add(mapToProductCategoryDto(productCategory));
        }
        return result;
    }

    public List<ProductDTO> getProductsByCategory(String category) {
        Integer categroyId = categoriesMapping.get(category);
        Set<Product> products = productService.getProductsByCategory(categroyId);
        List<ProductDTO> result = new ArrayList<>();
        for (Product product : products) {
            result.add(mapToProductDto(product));
        }
        return result;
    }

    public String getProductlabelFromImage(MultipartFile image) {
        try {
            byte[] fileBytes = image.getBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);

            String url = imageProcessingServerUrl + "/uploadImage";
            String requestBody = "\"" + base64Encoded + "\"";// JSON payload

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

            HttpStatus statusCode = responseEntity.getStatusCode();
            String responseBody = responseEntity.getBody()
                    .replace("\n", "")
                    .replace("\"", "");
            if (statusCode == HttpStatus.OK) {
                return responseBody;
            } else {
                throw new RuntimeException("invalid Image");
            }
        } catch (Exception e) {
            throw new RuntimeException("file not able to process");
        }
    }

    public List<ProductDTO> getProductsByCategories(List<String> categories) {
        List<Integer> categoryIds = new ArrayList<>();
        for (String categroy : categories) {
            categoryIds.add(categoriesMapping.get(categroy));
        }
        List<Product> products = productService.getProductsByCategories(categoryIds);
        List<ProductDTO> result = new ArrayList<>();
        for (Product product : products) {
            result.add(mapToProductDto(product));
        }
        return result;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        for (Product product : products) {

            result.add(mapToProductDto(product));
        }
        return result;
    }

    public List<ProductDTO> searchProductsByName(String productName) {
        List<Product> products;
        if (productName.isEmpty()) {
            products = productService.getAllProducts();
        }
        products = productService.searchproductUsingName(productName);
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        for (Product product : products) {
            result.add(mapToProductDto(product));
        }
        return result;
    }

    public List<ProductDTO> getProductsByTag(String productTag) {
        List<Product> products = productService.getproductByTag(productTag);
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        for (Product product : products) {
            result.add(mapToProductDto(product));
        }
        return result;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = productService.getPerfumeById(productDTO.getProductId());
        if (product == null) {
            throw new InvalidInputException("Product not Exist");
        }

        ProductCategory productCategory = productService.getCategoryById(categoriesMapping.get(productDTO.getProductCategory()));
        product.setSeller(productDTO.getSeller());
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setImagePath(productDTO.getImagePath());
        product.setProductTag(productDTO.getProductTag());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productCategory);

        productService.saveProduct(product);
        return productDTO;
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = mapToProductEntity(productDTO);
        ProductCategory productCategory = productService
                .getCategoryById(categoriesMapping.get(productDTO.getProductCategory()));
        product.setCategory(productCategory);
        // if (!file.isEmpty()) {
        // try {
        // // Create the upload directory if it doesn't exist
        // Path uploadPath = Paths.get(UPLOAD_DIR_Product);
        // if (!Files.exists(uploadPath)) {
        // Files.createDirectories(uploadPath);
        // }
        //
        // // Generate a unique file name to avoid overwriting existing files
        // String originalFilename = file.getOriginalFilename();
        // Path filePath = uploadPath.resolve(originalFilename);
        //
        // // Save the file to the directory
        // file.transferTo(filePath.toFile());
        //
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        return commonMapper.convertToResponse(productService.saveProduct(product), ProductDTO.class);
    }

    public String deleteProduct(Integer perfumeId) {
        return productService.deletePerfume(perfumeId);
    }

    public String addCategory(ProductCategory category) {
        productService.saveProductCategory(category);
        return "saved sucessfully";
    }

    private ProductDTO mapToProductDto(Product product) {
        ProductDTO result = new ProductDTO();
        result.setProductId(product.getId());
        result.setProductName(product.getProductName());
        result.setImagePath(product.getImagePath());
        result.setProductTag(product.getProductTag());
        result.setPrice(product.getPrice());
        result.setProductCategory(product.getCategory().getCategoryName());
        result.setSeller(product.getSeller());
        result.setDescription(product.getDescription());
        result.setRating(product.getRating());

        return result;
    }

    private Product mapToProductEntity(ProductDTO productDto) {
        Product result = new Product();
        // result.setId(productDto.getProductId());
        result.setProductName(productDto.getProductName());
        result.setPrice(productDto.getPrice());
        result.setImagePath(productDto.getImagePath());
        result.setProductTag(productDto.getProductTag());
        result.setDescription(productDto.getDescription());
        result.setSeller(productDto.getSeller());
        result.setRating(productDto.getRating());
        return result;
    }

    private ProductCategoryDto mapToProductCategoryDto(ProductCategory productCategory) {
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        productCategoryDto.setCategroyId(productCategory.getId());
        productCategoryDto.setCategory(productCategory.getCategoryName());
        return productCategoryDto;
    }

    private static Map<String, Integer> initializeCategories() {
        Map<String, Integer> categories = new HashMap<String, Integer>();

        categories.put("Fresh Fruits", 1);
        categories.put("Fresh Vegetables", 2);
        categories.put("Meat and Seafood", 3);
        categories.put("Cleaning Essentials", 4);
        categories.put("Home and Kitchen", 5);
        categories.put("Beauty and Grooming", 6);

        return categories;
    }

}
