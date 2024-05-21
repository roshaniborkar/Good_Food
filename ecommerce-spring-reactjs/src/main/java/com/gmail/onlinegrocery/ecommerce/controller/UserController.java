package com.gmail.onlinegrocery.ecommerce.controller;

import com.gmail.onlinegrocery.ecommerce.dto.GraphQLRequest;
import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductDTO;
import com.gmail.onlinegrocery.ecommerce.dto.user.UpdateUserRequest;
import com.gmail.onlinegrocery.ecommerce.dto.user.UserResponse;
import com.gmail.onlinegrocery.ecommerce.mapper.UserMapper;
import com.gmail.onlinegrocery.ecommerce.security.UserPrincipal;
import com.gmail.onlinegrocery.ecommerce.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.gmail.onlinegrocery.ecommerce.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_USERS)
public class UserController {

    private final UserMapper userMapper;
    private final GraphQLProvider graphQLProvider;

    @GetMapping
    public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(userMapper.getUserInfo(user.getEmail()));
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUserInfo(@AuthenticationPrincipal UserPrincipal user,
                                                       @Valid @RequestBody UpdateUserRequest request,
                                                       BindingResult bindingResult) {
        return ResponseEntity.ok(userMapper.updateUserInfo(user.getEmail(), request, bindingResult));
    }

    @PostMapping(CART)
    public ResponseEntity<List<ProductDTO>> getCart(@RequestBody List<Integer> productIds) {
        return ResponseEntity.ok(userMapper.getCart(productIds));
    }

    @PostMapping(GRAPHQL)
    public ResponseEntity<ExecutionResult> getUserInfoByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
}
