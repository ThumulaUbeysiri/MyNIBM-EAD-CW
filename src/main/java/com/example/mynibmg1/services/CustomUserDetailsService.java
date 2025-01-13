package com.example.mynibmg1.services;


import com.example.mynibmg1.models.Users;
import com.example.mynibmg1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load the user from the database (or in-memory storage)
        Optional<Users> users = userRepository.findByUserName(username);

        if (users == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Return the user details as an instance of org.springframework.security.core.userdetails.User
        return new org.springframework.security.core.userdetails.User(
                users.get().getUserName(),
                users.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + users.get().getRole()))
        );
    }


}
