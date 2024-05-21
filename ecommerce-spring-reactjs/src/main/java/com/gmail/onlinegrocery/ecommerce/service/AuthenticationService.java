package com.gmail.onlinegrocery.ecommerce.service;

import com.gmail.onlinegrocery.ecommerce.domain.User;
import com.gmail.onlinegrocery.ecommerce.dto.auth.AuthSuccessResponse;

import java.util.Map;

public interface AuthenticationService {

    Map<String, Object>  login(String email, String password);

    String registerUser(User user, String captcha, String password2);


    String activateUser(String code);

    String getEmailByPasswordResetCode(String code);

    String sendPasswordResetCode(String email);

    String passwordReset(String email, String password, String password2);
}
