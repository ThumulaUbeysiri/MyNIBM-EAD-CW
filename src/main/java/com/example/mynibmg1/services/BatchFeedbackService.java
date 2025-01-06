package com.example.mynibmg1.services;

import com.example.mynibmg1.DTOs.BatchFeedbackRequestDTO;
import com.example.mynibmg1.DTOs.BatchFeedbackResponseDTO;
import com.example.mynibmg1.models.Batch;
import com.example.mynibmg1.models.BatchFeedback;
import com.example.mynibmg1.models.Teacher;
import com.example.mynibmg1.repositories.BatchFeedbackRepository;
import com.example.mynibmg1.repositories.BatchRepository;
import com.example.mynibmg1.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchFeedbackService {

    @Autowired
    private BatchFeedbackRepository batchFeedbackRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public BatchFeedbackResponseDTO addBatchFeedback(BatchFeedbackRequestDTO requestDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(requestDTO.getTeacherUserID());
        if (teacher.isEmpty()) {
            throw new IllegalArgumentException("Teacher does not exist!");
        }

        Optional<Batch> batch = batchRepository.findById(requestDTO.getBatchID());
        if (batch.isEmpty()) {
            throw new IllegalArgumentException("Batch does not exist!");
        }

        BatchFeedback feedback = new BatchFeedback();
        feedback.setDate(requestDTO.getDate());
        feedback.setContent(requestDTO.getContent());
        feedback.setTeacher(teacher.get());
        feedback.setBatch(batch.get());

        BatchFeedback savedFeedback = batchFeedbackRepository.save(feedback);
        return mapToResponseDTO(savedFeedback);
    }

    public List<BatchFeedbackResponseDTO> getFeedbacksByBatch(Integer batchId) {
        List<BatchFeedback> feedbacks = batchFeedbackRepository.findByBatch_BatchID(batchId);
        return feedbacks.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    public void deleteBatchFeedback(Integer feedbackId) {
        if (!batchFeedbackRepository.existsById(feedbackId)) {
            throw new IllegalArgumentException("BatchFeedback not found!");
        }
        batchFeedbackRepository.deleteById(feedbackId);
    }

    public BatchFeedbackResponseDTO updateBatchFeedback(Integer feedbackId, BatchFeedbackRequestDTO requestDTO) {
        BatchFeedback feedback = batchFeedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalArgumentException("BatchFeedback not found!"));

        Optional<Teacher> teacher = teacherRepository.findById(requestDTO.getTeacherUserID());
        if (teacher.isEmpty()) {
            throw new IllegalArgumentException("Teacher does not exist!");
        }

        Optional<Batch> batch = batchRepository.findById(requestDTO.getBatchID());
        if (batch.isEmpty()) {
            throw new IllegalArgumentException("Batch does not exist!");
        }

        feedback.setDate(requestDTO.getDate());
        feedback.setContent(requestDTO.getContent());
        feedback.setTeacher(teacher.get());
        feedback.setBatch(batch.get());

        BatchFeedback updatedFeedback = batchFeedbackRepository.save(feedback);
        return mapToResponseDTO(updatedFeedback);
    }

    public List<BatchFeedbackResponseDTO> getFeedbacksByTeacher(Integer teacherUserId) {
        // Check if the teacher exists
        if (!teacherRepository.existsById(teacherUserId)) {
            throw new IllegalArgumentException("Teacher not found!");
        }

        // Fetch feedbacks by teacher's user ID
        List<BatchFeedback> feedbacks = batchFeedbackRepository.findByTeacher_UserId(teacherUserId);
        return feedbacks.stream().map(this::mapToResponseDTO).toList();
    }



    private BatchFeedbackResponseDTO mapToResponseDTO(BatchFeedback feedback) {
        BatchFeedbackResponseDTO responseDTO = new BatchFeedbackResponseDTO();
        responseDTO.setBatchFeedbackID(feedback.getBatchFeedbackID());
        responseDTO.setDate(feedback.getDate());
        responseDTO.setContent(feedback.getContent());
        responseDTO.setTeacherUserID(feedback.getTeacher().getUserId());
        responseDTO.setBatchID(feedback.getBatch().getBatchID());
        return responseDTO;
    }
}

