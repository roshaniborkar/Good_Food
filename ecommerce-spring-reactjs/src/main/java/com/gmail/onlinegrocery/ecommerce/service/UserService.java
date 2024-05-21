package com.gmail.onlinegrocery.ecommerce.service;

import com.gmail.onlinegrocery.ecommerce.domain.Product;
import com.gmail.onlinegrocery.ecommerce.domain.User;
import graphql.schema.DataFetcher;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getUserById(Long userId);

    User getUserInfo(String email);
    
    Page<User> getAllUsers(Pageable pageable);

    List<Product> getCart(List<Integer> perfumeIds);

    User updateUserInfo(String email, User user);

    DataFetcher<List<User>> getAllUsersByQuery();

    DataFetcher<User> getUserByQuery();
}
