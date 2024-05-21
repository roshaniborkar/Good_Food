package com.gmail.onlinegrocery.ecommerce.mapper;

import com.gmail.onlinegrocery.ecommerce.domain.Product;
import com.gmail.onlinegrocery.ecommerce.domain.User;
import com.gmail.onlinegrocery.ecommerce.dto.HeaderResponse;
import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductDTO;
import com.gmail.onlinegrocery.ecommerce.dto.user.BaseUserResponse;
import com.gmail.onlinegrocery.ecommerce.dto.user.UpdateUserRequest;
import com.gmail.onlinegrocery.ecommerce.dto.user.UserResponse;
import com.gmail.onlinegrocery.ecommerce.exception.InputFieldException;
import com.gmail.onlinegrocery.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CommonMapper commonMapper;
    private final UserService userService;

    public UserResponse getUserById(Long userId) {
        return commonMapper.convertToResponse(userService.getUserById(userId), UserResponse.class);
    }

    public UserResponse getUserInfo(String email) {
        return commonMapper.convertToResponse(userService.getUserInfo(email), UserResponse.class);
    }

    public List<ProductDTO> getCart(List<Integer> perfumesIds) {
        List<Product> products = userService.getCart(perfumesIds);
        List<ProductDTO> results = new ArrayList<>();
        for (Product product : products) {
            results.add(mapToProductDto(product));
        }

        return results;
    }

    public HeaderResponse<BaseUserResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        return commonMapper.getHeaderResponse(users.getContent(), users.getTotalPages(), users.getTotalElements(), BaseUserResponse.class);
    }

    public UserResponse updateUserInfo(String email, UpdateUserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        User user = commonMapper.convertToEntity(userRequest, User.class);
        return commonMapper.convertToResponse(userService.updateUserInfo(email, user), UserResponse.class);
    }

    private ProductDTO mapToProductDto(Product product) {
        ProductDTO result = new ProductDTO();
        result.setProductId(product.getId());
        result.setProductName(product.getProductName());
        result.setProductCategory(product.getCategory().getCategoryName());
        result.setPrice(product.getPrice());
        result.setImagePath(product.getImagePath());
        result.setProductTag(product.getProductTag());
        return result;
    }
}
