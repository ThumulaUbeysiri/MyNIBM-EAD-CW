package com.example.mynibmg1.controllers;

import com.example.mynibmg1.DTOs.BatchFeedbackRequestDTO;
import com.example.mynibmg1.DTOs.BatchFeedbackResponseDTO;
import com.example.mynibmg1.services.BatchFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch-feedback")
public class BatchFeedbackController {

    @Autowired
    private BatchFeedbackService batchFeedbackService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('TEACHER')")
    public BatchFeedbackResponseDTO addBatchFeedback(@RequestBody BatchFeedbackRequestDTO requestDTO) {
        return batchFeedbackService.addBatchFeedback(requestDTO);
    }

    @GetMapping("/get-by-batch/{batchId}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public List<BatchFeedbackResponseDTO> getFeedbacksByBatch(@PathVariable Integer batchId) {
        return batchFeedbackService.getFeedbacksByBatch(batchId);
    }

    @DeleteMapping("/delete/{feedbackId}")
    @PreAuthorize("hasRole('TEACHER')")
    public void deleteBatchFeedback(@PathVariable Integer feedbackId) {
        batchFeedbackService.deleteBatchFeedback(feedbackId);
    }

    @PutMapping("/update/{feedbackId}")
    @PreAuthorize("hasRole('TEACHER')")
    public BatchFeedbackResponseDTO updateBatchFeedback(
            @PathVariable Integer feedbackId,
            @RequestBody BatchFeedbackRequestDTO requestDTO) {
        return batchFeedbackService.updateBatchFeedback(feedbackId, requestDTO);
    }

    @GetMapping("/teacher/{teacherUserID}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity<List<BatchFeedbackResponseDTO>> getFeedbacksByTeacher(@PathVariable Integer teacherUserID) {
        List<BatchFeedbackResponseDTO> feedbacks = batchFeedbackService.getFeedbacksByTeacher(teacherUserID);
        return ResponseEntity.ok(feedbacks);
    }

}
