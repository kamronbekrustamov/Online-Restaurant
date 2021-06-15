package com.webproject.restaurant.controller;

import com.webproject.restaurant.dto.RegistrationRequest;
import com.webproject.restaurant.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login/")
    public String isAuthenticated() {
         return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
    }

    @PostMapping("/signup/")
    public RegistrationRequest signUp(@Valid @RequestBody RegistrationRequest request) {
        userService.signUp(request);
        return request;
    }
}
