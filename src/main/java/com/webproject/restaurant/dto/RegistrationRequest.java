package com.webproject.restaurant.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @NotBlank(message = "FirstName is required")
    private final String firstName;

    @NotBlank(message = "LastName is required")
    private final String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email")
    private final String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 32, message = "The password must be between 8 and 32 characters long")
    private final String password;
}
