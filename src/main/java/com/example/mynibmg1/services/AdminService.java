package com.example.mynibmg1.services;
import com.example.mynibmg1.DTOs.AdminRequestDto;
import com.example.mynibmg1.DTOs.AdminResponseDto;
import com.example.mynibmg1.models.Admin;
import com.example.mynibmg1.models.Users;
import com.example.mynibmg1.repositories.AdminRepository;
import com.example.mynibmg1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    public AdminService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AdminResponseDto addAdmin(AdminRequestDto adminDto) {
        // Check for existing email or username
        if (userRepository.findByEmail(adminDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use: " + adminDto.getEmail());
        }
        if (userRepository.findByUserName(adminDto.getUserName()).isPresent()) {
            throw new RuntimeException("Username already in use: " + adminDto.getUserName());
        }

        // Create and save User
        Users users = new Users();
        users.setUserName(adminDto.getUserName());
        users.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        users.setEmail(adminDto.getEmail());
        users.setRole(Users.Role.ADMIN);
        Users savedUsers = userRepository.save(users);

        // Create and save Admin
        Admin admin = new Admin();
        admin.setContact(adminDto.getContact());
        admin.setUser(savedUsers);
        Admin savedAdmin = adminRepository.save(admin);

        return new AdminResponseDto(
                savedUsers.getUserId(),
                savedUsers.getUserName(),
                savedUsers.getEmail(),
                savedAdmin.getContact()
        );
    }

    public AdminResponseDto updateAdmin(Integer id, AdminRequestDto adminDto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));

        Users users = admin.getUser();
        users.setUserName(adminDto.getUserName());
        users.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        users.setEmail(adminDto.getEmail());
        admin.setContact(adminDto.getContact());

        Admin updatedAdmin = adminRepository.save(admin);

        return new AdminResponseDto(
                updatedAdmin.getUser().getUserId(),
                updatedAdmin.getUser().getUserName(),
                updatedAdmin.getUser().getEmail(),
                updatedAdmin.getContact()
        );
    }

    public void deleteAdmin(Integer id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
        adminRepository.delete(admin);
    }
}

