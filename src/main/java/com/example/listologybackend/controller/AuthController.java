package com.example.listologybackend.controller;

import com.example.listologybackend.model.User;
import com.example.listologybackend.repository.RoleRepository;
import com.example.listologybackend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken.";
        }
        user.setRoles(Collections.singleton(roleRepository.findByName("USER").orElseThrow()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return userRepository.findByUsername(user.getUsername())
                .map(u -> {
                    if (u.getPassword().equals(user.getPassword())) {
                        return "Login successful!";
                    } else {
                        return "Invalid credentials.";
                    }
                })
                .orElse("User not found.");
    }

}
