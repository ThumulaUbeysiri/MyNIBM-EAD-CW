package com.example.mynibmg1.controllers;

import com.example.mynibmg1.DTOs.StudentFeedbackRequestDTO;
import com.example.mynibmg1.DTOs.StudentFeedbackResponseDTO;
import com.example.mynibmg1.services.StudentFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-feedback")
public class StudentFeedbackController {

    @Autowired
    private StudentFeedbackService studentFeedbackService;

    @PostMapping("/add")
    public StudentFeedbackResponseDTO addStudentFeedback(@RequestBody StudentFeedbackRequestDTO requestDTO) {
        return studentFeedbackService.addStudentFeedback(requestDTO);
    }

    @GetMapping("/get-by-student/{studentId}")
    public List<StudentFeedbackResponseDTO> getFeedbacksByStudent(@PathVariable Integer studentId) {
        return studentFeedbackService.getFeedbacksByStudent(studentId);
    }

    @DeleteMapping("/delete/{feedbackId}")
    public void deleteStudentFeedback(@PathVariable Integer feedbackId) {
        studentFeedbackService.deleteStudentFeedback(feedbackId);
    }

    @PutMapping("/update/{feedbackId}")
    public StudentFeedbackResponseDTO updateStudentFeedback(
            @PathVariable Integer feedbackId,
            @RequestBody StudentFeedbackRequestDTO requestDTO) {
        return studentFeedbackService.updateStudentFeedback(feedbackId, requestDTO);
    }

}

