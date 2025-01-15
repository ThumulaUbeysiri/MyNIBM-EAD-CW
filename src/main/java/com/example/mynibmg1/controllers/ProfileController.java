package com.example.mynibmg1.controllers;

import com.example.mynibmg1.DTOs.ProfileRequestDTO;
import com.example.mynibmg1.DTOs.ProfileResponseDTO;
import com.example.mynibmg1.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // Add a new profile
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<ProfileResponseDTO> addProfile(@RequestBody ProfileRequestDTO requestDTO) {
        ProfileResponseDTO responseDTO = profileService.addProfile(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // View student profile by student ID
    @GetMapping("/get/{userID}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity<ProfileResponseDTO> getProfileByStudentID(@PathVariable Integer userID) {
        ProfileResponseDTO responseDTO = profileService.getProfileByStudentID(userID);
        return ResponseEntity.ok(responseDTO);
    }

    // Update student profile
    @PutMapping("/update/{userID}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Integer userID,
            @RequestBody ProfileRequestDTO requestDTO) {
        if (!userID.equals(requestDTO.getUserId())) {
            throw new IllegalArgumentException("Mismatch between path variable userID and request body userId.");
        }
        ProfileResponseDTO responseDTO = profileService.updateProfile(userID, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}

