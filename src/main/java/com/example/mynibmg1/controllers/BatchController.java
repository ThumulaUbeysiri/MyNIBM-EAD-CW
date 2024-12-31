package com.example.mynibmg1.controllers;


import com.example.mynibmg1.DTOs.BatchRequestDTO;
import com.example.mynibmg1.DTOs.BatchResponseDTO;
import com.example.mynibmg1.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping("/add")
    public ResponseEntity<BatchResponseDTO> addBatch(@RequestBody BatchRequestDTO batchRequestDTO) {
        BatchResponseDTO responseDTO = batchService.addBatch(batchRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/get/{teacherUserID}")
    public ResponseEntity<List<BatchResponseDTO>> getBatchesByTeacher(@PathVariable String teacherUserID) {
        List<BatchResponseDTO> responseDTOs = batchService.getBatchesByTeacher(Integer.valueOf(teacherUserID));
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/update/{batchID}")
    public ResponseEntity<BatchResponseDTO> updateBatch(@PathVariable Integer batchID,
                                                        @RequestBody BatchRequestDTO batchRequestDTO) {
        BatchResponseDTO responseDTO = batchService.updateBatch(batchID, batchRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}

