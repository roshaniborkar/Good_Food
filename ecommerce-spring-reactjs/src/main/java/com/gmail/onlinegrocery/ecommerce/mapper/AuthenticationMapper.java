package com.gmail.onlinegrocery.ecommerce.mapper;

import com.gmail.onlinegrocery.ecommerce.domain.User;
import com.gmail.onlinegrocery.ecommerce.dto.PasswordResetRequest;
import com.gmail.onlinegrocery.ecommerce.dto.RegistrationRequest;
import com.gmail.onlinegrocery.ecommerce.dto.auth.AuthSuccessResponse;
import com.gmail.onlinegrocery.ecommerce.dto.auth.AuthenticationRequest;
import com.gmail.onlinegrocery.ecommerce.dto.auth.AuthenticationResponse;
import com.gmail.onlinegrocery.ecommerce.dto.user.UserResponse;
import com.gmail.onlinegrocery.ecommerce.exception.InputFieldException;
import com.gmail.onlinegrocery.ecommerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {

    private final AuthenticationService authenticationService;
    private final CommonMapper commonMapper;

    public AuthenticationResponse login(AuthenticationRequest request) {
        Map<String, Object> credentials = authenticationService.login(request.getEmail(), request.getPassword());
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUser(commonMapper.convertToResponse(credentials.get("user"), UserResponse.class));
        response.setToken((String) credentials.get("token"));
        return response;
    }

    public String getEmailByPasswordResetCode(String code) {
        return authenticationService.getEmailByPasswordResetCode(code);
    }

    public String registerUser(String captcha, RegistrationRequest registrationRequest) {
        User user = commonMapper.convertToEntity(registrationRequest, User.class);
        return authenticationService.registerUser(user, captcha, registrationRequest.getPassword2());
    }

    public String activateUser(String code) {
        return authenticationService.activateUser(code);
    }

    public String sendPasswordResetCode(String email) {
        return authenticationService.sendPasswordResetCode(email);
    }

    public String passwordReset(String email, PasswordResetRequest passwordReset) {
        return authenticationService.passwordReset(email, passwordReset.getPassword(), passwordReset.getPassword2());
    }

    public String passwordReset(String email, PasswordResetRequest passwordReset, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return authenticationService.passwordReset(email, passwordReset.getPassword(), passwordReset.getPassword2());
        }
    }
}
