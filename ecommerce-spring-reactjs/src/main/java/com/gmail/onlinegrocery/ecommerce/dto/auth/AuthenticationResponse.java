package com.gmail.onlinegrocery.ecommerce.dto.auth;

import com.gmail.onlinegrocery.ecommerce.dto.user.UserResponse;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private UserResponse user;
    private String token;
}
