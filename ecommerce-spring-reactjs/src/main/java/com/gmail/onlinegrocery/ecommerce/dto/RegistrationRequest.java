package com.gmail.onlinegrocery.ecommerce.dto;

import com.gmail.onlinegrocery.ecommerce.constants.ErrorMessage;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Locale;

@Data
public class RegistrationRequest {

    @NotBlank(message = "Fill captcha.")
    private String captcha;

    @NotBlank(message = ErrorMessage.EMPTY_FIRST_NAME)
    private String firstName;

    @NotBlank(message = ErrorMessage.EMPTY_LAST_NAME)
    private String lastName;

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD_CHARACTER_LENGTH)
    private String password;

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD2_CHARACTER_LENGTH)
    private String password2;

    @Email(message = ErrorMessage.INCORRECT_EMAIL)
    @NotBlank(message = ErrorMessage.EMAIL_CANNOT_BE_EMPTY)
    private String email;
    private String address;
    private String city;
    private String phoneNumber;

    public @Email(message = ErrorMessage.INCORRECT_EMAIL) @NotBlank(message = ErrorMessage.EMAIL_CANNOT_BE_EMPTY) String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = ErrorMessage.INCORRECT_EMAIL) @NotBlank(message = ErrorMessage.EMAIL_CANNOT_BE_EMPTY) String email) {
        this.email = email.toLowerCase(Locale.ROOT);
    }
}
