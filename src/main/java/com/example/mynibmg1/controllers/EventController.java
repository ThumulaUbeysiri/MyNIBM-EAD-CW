package com.example.mynibmg1.controllers;

import com.example.mynibmg1.DTOs.EventRequestDTO;
import com.example.mynibmg1.DTOs.EventResponseDTO;
import com.example.mynibmg1.DTOs.InterviewRequestDTO;
import com.example.mynibmg1.DTOs.WorkshopRequestDTO;
import com.example.mynibmg1.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Update Interview for a Batch
    @PostMapping("/interviews/{interviewId}/batch/{batchId}")
    public ResponseEntity<EventResponseDTO> updateInterviewForBatch(
            @PathVariable Integer interviewId,
            @PathVariable Integer batchId,
            @RequestBody InterviewRequestDTO requestDTO) {
        EventResponseDTO updatedEvent = eventService.updateInterviewForBatch(interviewId, batchId, requestDTO);
        return ResponseEntity.ok(updatedEvent);
    }


    // Update Interview for a Student
    @PutMapping("/interviews/{interviewId}/student/{studentId}")
    public ResponseEntity<EventResponseDTO> updateInterviewForStudent(
            @PathVariable Integer interviewId,
            @PathVariable Integer studentId,
            @RequestBody InterviewRequestDTO requestDTO) {
        EventResponseDTO updatedEvent = eventService.updateInterviewForStudent(interviewId, studentId, requestDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    // Update Workshop for a Batch
    @PutMapping("/workshops/{workshopId}/batch/{batchId}")
    public ResponseEntity<EventResponseDTO> updateWorkshopForBatch(
            @PathVariable Integer workshopId,
            @PathVariable Integer batchId,
            @RequestBody WorkshopRequestDTO requestDTO) {
        EventResponseDTO updatedEvent = eventService.updateWorkshopForBatch(workshopId, batchId, requestDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    // Update Workshop for a Student
    @PutMapping("/workshops/{workshopId}/student/{studentId}")
    public ResponseEntity<EventResponseDTO> updateWorkshopForStudent(
            @PathVariable Integer workshopId,
            @PathVariable Integer studentId,
            @RequestBody WorkshopRequestDTO requestDTO) {
        EventResponseDTO updatedEvent = eventService.updateWorkshopForStudent(workshopId, studentId, requestDTO);
        return ResponseEntity.ok(updatedEvent);
    }


    // Create Interview for a Batch
    @PostMapping("/interviews/batch/{batchId}")
    public ResponseEntity<EventResponseDTO> createInterviewForBatch(
            @RequestBody InterviewRequestDTO requestDTO,
            @PathVariable Integer batchId) {
        EventResponseDTO responseDTO = eventService.createInterviewForBatch(requestDTO, batchId);
        return ResponseEntity.ok(responseDTO);
    }

    // Create Interview for a Student
    @PostMapping("/interviews/student/{studentId}")
    public ResponseEntity<EventResponseDTO> createInterviewForStudent(
            @RequestBody InterviewRequestDTO requestDTO,
            @PathVariable Integer studentId) {
        EventResponseDTO responseDTO = eventService.createInterviewForStudent(requestDTO, studentId);
        return ResponseEntity.ok(responseDTO);
    }

    // Create Workshop for a Batch
    @PostMapping("/workshops/batch/{batchId}")
    public ResponseEntity<EventResponseDTO> createWorkshopForBatch(
            @RequestBody EventRequestDTO requestDTO,
            @PathVariable Integer batchId) {
        EventResponseDTO responseDTO = eventService.createWorkshopForBatch(requestDTO, batchId);
        return ResponseEntity.ok(responseDTO);
    }

    // Create Workshop for a Student
    @PostMapping("/workshops/student/{studentId}")
    public ResponseEntity<EventResponseDTO> createWorkshopForStudent(
            @RequestBody EventRequestDTO requestDTO,
            @PathVariable Integer studentId) {
        EventResponseDTO responseDTO = eventService.createWorkshopForStudent(requestDTO, studentId);
        return ResponseEntity.ok(responseDTO);
    }


    // ---- Get Methods ----

    // Get All Interviews Created by a Teacher
    @GetMapping("/interviews/teacher/{teacherId}")
    public ResponseEntity<List<EventResponseDTO>> getInterviewsByTeacher(@PathVariable Integer teacherId) {
        List<EventResponseDTO> interviews = eventService.getInterviewsByTeacher(teacherId);
        return ResponseEntity.ok(interviews);
    }

    // Get All Workshops Created by a Teacher
    @GetMapping("/workshops/teacher/{teacherId}")
    public ResponseEntity<List<EventResponseDTO>> getWorkshopsByTeacher(@PathVariable Integer teacherId) {
        List<EventResponseDTO> workshops = eventService.getWorkshopsByTeacher(teacherId);
        return ResponseEntity.ok(workshops);
    }

    // Get All Events for a Batch
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<EventResponseDTO>> getEventsForBatch(@PathVariable Integer batchId) {
        List<EventResponseDTO> events = eventService.getEventsForBatch(batchId);
        return ResponseEntity.ok(events);
    }

    // Get All Events for a Student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EventResponseDTO>> getEventsForStudent(@PathVariable Integer studentId) {
        List<EventResponseDTO> events = eventService.getEventsForStudent(studentId);
        return ResponseEntity.ok(events);
    }

    // Get All Interviews for a Batch
    @GetMapping("/interviews/batch/{batchId}")
    public ResponseEntity<List<EventResponseDTO>> getInterviewsByBatchId(@PathVariable Integer batchId) {
        List<EventResponseDTO> interviews = eventService.getInterviewsByBatchId(batchId);
        return ResponseEntity.ok(interviews);
    }

    // Get All Workshops for a Batch
    @GetMapping("/workshops/batch/{batchId}")
    public ResponseEntity<List<EventResponseDTO>> getWorkshopsByBatchId(@PathVariable Integer batchId) {
        List<EventResponseDTO> workshops = eventService.getWorkshopsByBatchId(batchId);
        return ResponseEntity.ok(workshops);
    }

    // Get All Interviews for a Student
    @GetMapping("/interviews/student/{studentId}")
    public ResponseEntity<List<EventResponseDTO>> getInterviewsByStudentId(@PathVariable Integer studentId) {
        List<EventResponseDTO> interviews = eventService.getInterviewsByStudentId(studentId);
        return ResponseEntity.ok(interviews);
    }

    // Get All Workshops for a Student
    @GetMapping("/workshops/student/{studentId}")
    public ResponseEntity<List<EventResponseDTO>> getWorkshopsByStudentId(@PathVariable Integer studentId) {
        List<EventResponseDTO> workshops = eventService.getWorkshopsByStudentId(studentId);
        return ResponseEntity.ok(workshops);
    }

    // ---- Delete Methods ----

    // Delete Interview for a Batch
    @DeleteMapping("/interviews/{interviewId}/batch/{batchId}")
    public ResponseEntity<Void> deleteInterviewForBatch(
            @PathVariable Integer interviewId, @PathVariable Integer batchId) {
        eventService.deleteInterviewForBatch(interviewId, batchId);
        return ResponseEntity.noContent().build();
    }

    // Delete Interview for a Student
    @DeleteMapping("/interviews/{interviewId}/student/{studentId}")
    public ResponseEntity<Void> deleteInterviewForStudent(
            @PathVariable Integer interviewId, @PathVariable Integer studentId) {
        eventService.deleteInterviewForStudent(interviewId, studentId);
        return ResponseEntity.noContent().build();
    }

    // Delete Workshop for a Batch
    @DeleteMapping("/workshops/{workshopId}/batch/{batchId}")
    public ResponseEntity<Void> deleteWorkshopForBatch(
            @PathVariable Integer workshopId, @PathVariable Integer batchId) {
        eventService.deleteWorkshopForBatch(workshopId, batchId);
        return ResponseEntity.noContent().build();
    }

    // Delete Workshop for a Student
    @DeleteMapping("/workshops/{workshopId}/student/{studentId}")
    public ResponseEntity<Void> deleteWorkshopForStudent(
            @PathVariable Integer workshopId, @PathVariable Integer studentId) {
        eventService.deleteWorkshopForStudent(workshopId, studentId);
        return ResponseEntity.noContent().build();
    }
}

