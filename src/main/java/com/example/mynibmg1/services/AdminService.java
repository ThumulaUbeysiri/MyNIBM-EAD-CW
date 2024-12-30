package com.example.mynibmg1.services;
import com.example.mynibmg1.DTOs.AdminRequestDto;
import com.example.mynibmg1.DTOs.AdminResponseDto;
import com.example.mynibmg1.models.Admin;
import com.example.mynibmg1.models.User;
import com.example.mynibmg1.repositories.AdminRepository;
import com.example.mynibmg1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    public AdminResponseDto addAdmin(AdminRequestDto adminDto) {
        // Check for existing email or username
        if (userRepository.findByEmail(adminDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use: " + adminDto.getEmail());
        }
        if (userRepository.findByUserName(adminDto.getUserName()).isPresent()) {
            throw new RuntimeException("Username already in use: " + adminDto.getUserName());
        }

        // Create and save User
        User user = new User();
        user.setUserName(adminDto.getUserName());
        user.setPassword(adminDto.getPassword());
        user.setEmail(adminDto.getEmail());
        user.setRole(User.Role.ADMIN);
        User savedUser = userRepository.save(user);

        // Create and save Admin
        Admin admin = new Admin();
        admin.setContact(adminDto.getContact());
        admin.setUser(savedUser);
        Admin savedAdmin = adminRepository.save(admin);

        return new AdminResponseDto(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedAdmin.getContact()
        );
    }

    public AdminResponseDto updateAdmin(Integer id, AdminRequestDto adminDto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));

        User user = admin.getUser();
        user.setUserName(adminDto.getUserName());
        user.setPassword(adminDto.getPassword());
        user.setEmail(adminDto.getEmail());
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

