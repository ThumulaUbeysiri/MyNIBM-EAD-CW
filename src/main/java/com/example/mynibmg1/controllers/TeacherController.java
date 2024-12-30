package com.example.mynibmg1.controllers;

import com.example.mynibmg1.DTOs.TeacherRequestDto;
import com.example.mynibmg1.DTOs.TeacherResponseDto;
import com.example.mynibmg1.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    public ResponseEntity<TeacherResponseDto> addTeacher(@RequestBody TeacherRequestDto teacherDto) {
        return ResponseEntity.ok(teacherService.addTeacher(teacherDto));
    }

    @GetMapping("/get")
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Integer id) {
//        return ResponseEntity.ok(teacherService.getTeacherById(id));
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable Integer id, @RequestBody TeacherRequestDto teacherDto) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacherDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}

