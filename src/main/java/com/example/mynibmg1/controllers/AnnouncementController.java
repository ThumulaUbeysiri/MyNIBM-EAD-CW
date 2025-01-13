package com.example.mynibmg1.controllers;

import com.example.mynibmg1.DTOs.AnnouncementRequestDTO;
import com.example.mynibmg1.DTOs.AnnouncementResponseDTO;
import com.example.mynibmg1.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    // Add a new announcement
    @PostMapping("/add")
    public ResponseEntity<AnnouncementResponseDTO> addAnnouncement(
            @RequestBody AnnouncementRequestDTO requestDTO) {
        AnnouncementResponseDTO responseDTO = announcementService.addAnnouncement(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Get announcements made by a specific teacher
    @GetMapping("/teacher/{teacherID}")
    public ResponseEntity<List<AnnouncementResponseDTO>> getAnnouncementsByTeacher(
            @PathVariable Integer teacherID) {
        List<AnnouncementResponseDTO> responseDTOs = announcementService.getAnnouncementsByTeacher(teacherID);
        return ResponseEntity.ok(responseDTOs);
    }

    // Update an existing announcement
    @PutMapping("/update/{announceID}")
    public ResponseEntity<AnnouncementResponseDTO> updateAnnouncement(
            @PathVariable Integer announceID, @RequestBody AnnouncementRequestDTO requestDTO) {
        AnnouncementResponseDTO responseDTO = announcementService.updateAnnouncement(announceID, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // View announcements given to a student
    @GetMapping("/student/{studentID}")
    public ResponseEntity<List<AnnouncementResponseDTO>> getAnnouncementsForStudent(
            @PathVariable Integer studentID) {
        List<AnnouncementResponseDTO> responseDTOs = announcementService.getAnnouncementsForStudent(studentID);
        return ResponseEntity.ok(responseDTOs);
    }

    // View announcements given to a batch
    @GetMapping("/batch/{batchID}")
    public ResponseEntity<List<AnnouncementResponseDTO>> getAnnouncementsForBatch(
            @PathVariable Integer batchID) {
        List<AnnouncementResponseDTO> responseDTOs = announcementService.getAnnouncementsForBatch(batchID);
        return ResponseEntity.ok(responseDTOs);
    }
}

