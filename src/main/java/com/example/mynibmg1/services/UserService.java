package com.example.mynibmg1.services;


import com.example.mynibmg1.models.Users;
import com.example.mynibmg1.repositories.UserRepository;
import com.example.mynibmg1.Utils.JwtUtil;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Autowired
    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }



    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Users> user = userRepository.findByUserName(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Users foundUser = user.get();

        UserDetails userDetails = User.builder()
                .username(foundUser.getUserName())
                .password(foundUser.getPassword())
                .roles(foundUser.getRole().name()) // Convert Role enum to String
                .build();

        return userDetails;
    }


    public Users registerUser(Users users) {

        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    public String verifyUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                String role = getUserRole(username); // Fetch role of the user
                String token = jwtUtil.generateToken(username, role); // Generate JWT token
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "bad credentials"; // Handle bad credentials
        }
        return "fail";
    }


    public String getUserRole(String username) {
        Optional<Users> user = userRepository.findByUserName(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }
        return user.get().getRole().name();
    }

}
