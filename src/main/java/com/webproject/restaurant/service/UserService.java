package com.webproject.restaurant.service;

import com.webproject.restaurant.dto.RegistrationRequest;
import com.webproject.restaurant.model.User;
import com.webproject.restaurant.model.enums.UserRole;
import com.webproject.restaurant.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found"));
    }



    public void signUp(RegistrationRequest request) {
        boolean isUserPresent = userRepository.findByEmail(request.getEmail()).isPresent();
        if(isUserPresent) {
            throw new IllegalStateException("Email " + request.getEmail() + " is already taken");
        }
        String password = passwordEncoder.encode(request.getPassword());
        User user = new User(
                null,
                request.getFirstName(), request.getLastName(), request.getEmail(), password, UserRole.ROLE_USER);
        userRepository.save(user);
    }
}
