package com.example.mynibmg1.controllers;

import com.example.mynibmg1.models.Users;
import com.example.mynibmg1.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/auth")
public class UserController {

    UserService UserService;

    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        String username = users.getUserName();
        String password = users.getPassword();

        // Authenticate the user and generate a token
        String token = UserService.verifyUser(username, password);
        if ("bad credentials".equals(token) || "fail".equals(token)) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }

        // Return the generated token
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registrationRequest) {
        try {
            String username = registrationRequest.get("username");
            String password = registrationRequest.get("password");
            String role = registrationRequest.get("role");
            String name = registrationRequest.get("name");

            // Create a new Users object
            Users user = new Users(username, password, role,name); // No id required

            // Register the user
//            UserService.registerUser(user);

            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        }
        catch (Exception e){
            return ResponseEntity.ok(Map.of("error: ", e.getMessage()));

        }
    }
}
