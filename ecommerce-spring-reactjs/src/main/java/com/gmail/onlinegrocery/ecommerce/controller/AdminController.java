package com.gmail.onlinegrocery.ecommerce.controller;

import com.gmail.onlinegrocery.ecommerce.domain.ProductCategory;
import com.gmail.onlinegrocery.ecommerce.dto.HeaderResponse;
import com.gmail.onlinegrocery.ecommerce.dto.order.OrderResponse;
import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductDTO;
import com.gmail.onlinegrocery.ecommerce.dto.user.BaseUserResponse;
import com.gmail.onlinegrocery.ecommerce.dto.user.UserResponse;
import com.gmail.onlinegrocery.ecommerce.mapper.OrderMapper;
import com.gmail.onlinegrocery.ecommerce.mapper.ProductMapper;
import com.gmail.onlinegrocery.ecommerce.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.gmail.onlinegrocery.ecommerce.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_ADMIN)
public class AdminController {

    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    @PostMapping(ADD)
    public ResponseEntity<ProductDTO> addProduct(@RequestPart("product") ProductDTO product) {
        return ResponseEntity.ok(productMapper.saveProduct(product));
    }

    @PostMapping(ADDCategory)
    public ResponseEntity<String> addProductCategory(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(productMapper.addCategory(productCategory));
    }

    @PostMapping(EDIT)
    public ResponseEntity<ProductDTO> updateProduct(@RequestPart("product") ProductDTO product) {
        return ResponseEntity.ok(productMapper.updateProduct(product));
    }

    @DeleteMapping(DELETE_BY_PRODUCT_ID)
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
        return ResponseEntity.ok(productMapper.deleteProduct(productId));
    }

    @GetMapping(ORDERS)
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<OrderResponse> response = orderMapper.getAllOrders(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping(ORDER_BY_EMAIL)
    public ResponseEntity<List<OrderResponse>> getUserOrdersByEmail(@PathVariable String userEmail) {
        List<OrderResponse> result = orderMapper.getUserOrders(userEmail);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(ORDER_DELETE)
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.deleteOrder(orderId));
    }

    @GetMapping(USER_BY_ID)
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper.getUserById(userId));
    }

    @GetMapping(USER_ALL)
    public ResponseEntity<List<BaseUserResponse>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<BaseUserResponse> response = userMapper.getAllUsers(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @PostMapping(UploadImage)
    public ResponseEntity<List<ProductDTO>> productImageCheck(
            @RequestParam(value = "file", required = true) MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        String productlabel = productMapper.getProductlabelFromImage(file);

        List<ProductDTO> result = productMapper.getProductsByTag(productlabel);
        return ResponseEntity.ok().body(result);
    }
}
