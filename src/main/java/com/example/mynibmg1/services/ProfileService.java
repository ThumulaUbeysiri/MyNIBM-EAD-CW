package com.example.mynibmg1.services;

import com.example.mynibmg1.DTOs.ProfileRequestDTO;
import com.example.mynibmg1.DTOs.ProfileResponseDTO;
import com.example.mynibmg1.models.Profile;
import com.example.mynibmg1.models.Student;
import com.example.mynibmg1.repositories.ProfileRepository;
import com.example.mynibmg1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Add a new profile
    public ProfileResponseDTO addProfile(ProfileRequestDTO requestDTO) {
        // Check if the student exists
        Student student = studentRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Student not found for ID: " + requestDTO.getUserId()));

        // Ensure the student doesn't already have a profile
        if (profileRepository.findByStudentUserId(requestDTO.getUserId()) != null) {
            throw new RuntimeException("Profile already exists for student ID: " + requestDTO.getUserId());
        }

        // Create and save the new profile
        Profile profile = new Profile();
        profile.setFirstName(requestDTO.getFirstName());
        profile.setLastName(requestDTO.getLastName());
        profile.setAddress(requestDTO.getAddress());
        profile.setBio(requestDTO.getBio());
        profile.setStudent(student);

        profile = profileRepository.save(profile);
        return mapToResponseDTO(profile);
    }

    // Get profile by student ID
    public ProfileResponseDTO getProfileByStudentID(Integer userID) {
        Profile profile = profileRepository.findByStudentUserId(userID);
        if (profile == null) {
            throw new RuntimeException("Profile not found for student ID: " + userID);
        }
        return mapToResponseDTO(profile);
    }

    // Update an existing profile
    public ProfileResponseDTO updateProfile(Integer userID, ProfileRequestDTO requestDTO) {
        // Retrieve the profile by student ID
        Profile profile = profileRepository.findByStudentUserId(userID);
        if (profile == null) {
            throw new RuntimeException("Profile not found for student ID: " + userID);
        }

        // Validate userId in requestDTO
        Student student = studentRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Student not found for ID: " + userID));
        if (!student.getUserId().equals(requestDTO.getUserId())) {
            throw new RuntimeException("Mismatch in user ID between profile and student.");
        }

        // Update profile details
        profile.setFirstName(requestDTO.getFirstName());
        profile.setLastName(requestDTO.getLastName());
        profile.setAddress(requestDTO.getAddress());
        profile.setBio(requestDTO.getBio());

        profile = profileRepository.save(profile);
        return mapToResponseDTO(profile);
    }

    // Map Profile entity to ResponseDTO
    private ProfileResponseDTO mapToResponseDTO(Profile profile) {
        ProfileResponseDTO responseDTO = new ProfileResponseDTO();
        responseDTO.setProfileID(profile.getProfileID());
        responseDTO.setFirstName(profile.getFirstName());
        responseDTO.setLastName(profile.getLastName());
        responseDTO.setAddress(profile.getAddress());
        responseDTO.setBio(profile.getBio());
        responseDTO.setUserId(profile.getStudent().getUserId());
        return responseDTO;
    }
}
