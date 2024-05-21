package com.gmail.onlinegrocery.ecommerce.controller;

import com.gmail.onlinegrocery.ecommerce.dto.RegistrationRequest;
import com.gmail.onlinegrocery.ecommerce.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.gmail.onlinegrocery.ecommerce.constants.PathConstants.ACTIVATE_CODE;
import static com.gmail.onlinegrocery.ecommerce.constants.PathConstants.API_V1_REGISTRATION;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_REGISTRATION)
public class RegistrationController {

    private final AuthenticationMapper authenticationMapper;

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody RegistrationRequest user) {
        return ResponseEntity.ok(authenticationMapper.registerUser(user.getCaptcha(), user));
    }

    @GetMapping(ACTIVATE_CODE)
    public ResponseEntity<String> activateEmailCode(@PathVariable String code) {
        return ResponseEntity.ok(authenticationMapper.activateUser(code));
    }
}
