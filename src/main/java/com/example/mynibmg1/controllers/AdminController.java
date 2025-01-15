package com.example.mynibmg1.controllers;
import com.example.mynibmg1.DTOs.AdminRequestDto;
import com.example.mynibmg1.DTOs.AdminResponseDto;
import com.example.mynibmg1.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminResponseDto> addAdmin(@RequestBody AdminRequestDto adminDto) {
        AdminResponseDto response = adminService.addAdmin(adminDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminResponseDto> updateAdmin(@PathVariable Integer id, @RequestBody AdminRequestDto adminDto) {
        AdminResponseDto response = adminService.updateAdmin(id, adminDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
