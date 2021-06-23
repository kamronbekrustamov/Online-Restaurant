package com.webproject.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
