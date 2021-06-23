package com.webproject.restaurant.controller;

import com.webproject.restaurant.dto.AuthenticationResponse;
import com.webproject.restaurant.dto.RegistrationRequest;
import com.webproject.restaurant.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate() {
         return userService.authenticate();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        return userService.register(request);
    }
}
