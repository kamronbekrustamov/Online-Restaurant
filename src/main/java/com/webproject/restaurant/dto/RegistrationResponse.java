package com.webproject.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationResponse {
    private String token;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
