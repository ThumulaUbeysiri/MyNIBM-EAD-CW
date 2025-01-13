package com.example.mynibmg1.controllers;

import com.example.mynibmg1.DTOs.StudentRequestDTO;
import com.example.mynibmg1.DTOs.StudentResponseDTO;
import com.example.mynibmg1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    // Get all students created by a specific Admin
    @GetMapping("/get/{adminUserID}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByAdmin(@PathVariable Integer adminUserID) {
        List<StudentResponseDTO> students = studentService.getStudentsByAdminUserId(adminUserID);
        return ResponseEntity.ok(students);
    }

    // Add a new student
    @PostMapping("/add")
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentRequestDTO requestDTO) {
        return ResponseEntity.ok(studentService.addStudent(requestDTO));
    }

    // Update an existing student
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Integer id,
            @RequestBody StudentRequestDTO requestDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, requestDTO));
    }

    // Delete a student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
