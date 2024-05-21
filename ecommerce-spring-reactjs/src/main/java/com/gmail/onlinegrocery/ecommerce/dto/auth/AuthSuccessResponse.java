package com.gmail.onlinegrocery.ecommerce.dto.auth;

import com.gmail.onlinegrocery.ecommerce.enums.Role;
import lombok.Data;

@Data
public class AuthSuccessResponse {
    private String email;
    private Role Role;
}
