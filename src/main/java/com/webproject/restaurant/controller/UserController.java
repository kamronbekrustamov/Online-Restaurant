package com.webproject.restaurant.controller;

import com.webproject.restaurant.dto.AuthAndRegistrationResponse;
import com.webproject.restaurant.dto.RegistrationRequest;
import com.webproject.restaurant.model.User;
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

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/authenticate")
    public AuthAndRegistrationResponse authenticate() {
         return userService.authenticate();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        return userService.register(request);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
