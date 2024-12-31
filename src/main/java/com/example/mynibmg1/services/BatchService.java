package com.example.mynibmg1.services;

import com.example.mynibmg1.DTOs.BatchRequestDTO;
import com.example.mynibmg1.DTOs.BatchResponseDTO;
import com.example.mynibmg1.models.Batch;
import com.example.mynibmg1.models.Admin;
import com.example.mynibmg1.models.Teacher;
import com.example.mynibmg1.repositories.BatchRepository;
import com.example.mynibmg1.repositories.TeacherRepository;
import com.example.mynibmg1.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdminRepository adminRepository;

    public BatchResponseDTO addBatch(BatchRequestDTO batchRequestDTO) {
        if (batchRepository.existsByBatchName(batchRequestDTO.getBatchName())) {
            throw new IllegalArgumentException("Batch name already exists!");
        }

        // Validate teacher existence
        Optional<Teacher> teacher = teacherRepository.findById(batchRequestDTO.getTeacherUserID());
        if (teacher.isEmpty()) {
            throw new IllegalArgumentException("Assigned teacher does not exist!");
        }

        // Validate admin existence
        Optional<Admin> admin = adminRepository.findById(batchRequestDTO.getAdminUserID());
        if (admin.isEmpty()) {
            throw new IllegalArgumentException("Assigned admin does not exist!");
        }

        Batch batch = new Batch();
        batch.setBatchName(batchRequestDTO.getBatchName());
        batch.setStartYear(batchRequestDTO.getStartYear());
        batch.setEndYear(batchRequestDTO.getEndYear());
        batch.setAdmin(admin.get());
        batch.setTeacher(teacher.get());

        Batch savedBatch = batchRepository.save(batch);
        return mapToResponseDTO(savedBatch);
    }

    public List<BatchResponseDTO> getBatchesByTeacher(Integer teacherUserID) {
        List<Batch> batches = batchRepository.findByTeacher_UserId(teacherUserID);
        return batches.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    public BatchResponseDTO updateBatch(Integer batchID, BatchRequestDTO batchRequestDTO) {
        Optional<Batch> existingBatch = batchRepository.findById(batchID);
        if (existingBatch.isEmpty()) {
            throw new IllegalArgumentException("Batch not found with ID: " + batchID);
        }

        Batch batch = existingBatch.get();

        // Validate teacher existence
        Optional<Teacher> teacher = teacherRepository.findById(batchRequestDTO.getTeacherUserID());
        if (teacher.isEmpty()) {
            throw new IllegalArgumentException("Assigned teacher does not exist!");
        }

        // Validate admin existence
        Optional<Admin> admin = adminRepository.findById(batchRequestDTO.getAdminUserID());
        if (admin.isEmpty()) {
            throw new IllegalArgumentException("Assigned admin does not exist!");
        }

        if (!batch.getBatchName().equals(batchRequestDTO.getBatchName())
                && batchRepository.existsByBatchName(batchRequestDTO.getBatchName())) {
            throw new IllegalArgumentException("Batch name already exists!");
        }

        batch.setBatchName(batchRequestDTO.getBatchName());
        batch.setStartYear(batchRequestDTO.getStartYear());
        batch.setEndYear(batchRequestDTO.getEndYear());
        batch.setAdmin(admin.get());
        batch.setTeacher(teacher.get());

        Batch updatedBatch = batchRepository.save(batch);
        return mapToResponseDTO(updatedBatch);
    }

    private BatchResponseDTO mapToResponseDTO(Batch batch) {
        BatchResponseDTO responseDTO = new BatchResponseDTO();
        responseDTO.setBatchID(batch.getBatchID());
        responseDTO.setBatchName(batch.getBatchName());
        responseDTO.setStartYear(batch.getStartYear());
        responseDTO.setEndYear(batch.getEndYear());
        responseDTO.setAdminUserID(batch.getAdmin().getUserId());
        responseDTO.setTeacherUserID(batch.getTeacher().getUserId());
        return responseDTO;
    }
}
