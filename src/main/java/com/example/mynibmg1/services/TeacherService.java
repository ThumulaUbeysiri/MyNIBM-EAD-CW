package com.example.mynibmg1.services;
import com.example.mynibmg1.DTOs.TeacherRequestDto;
import com.example.mynibmg1.DTOs.TeacherResponseDto;
import com.example.mynibmg1.models.Admin;
import com.example.mynibmg1.models.Teacher;
import com.example.mynibmg1.models.User;
import com.example.mynibmg1.repositories.AdminRepository;
import com.example.mynibmg1.repositories.TeacherRepository;
import com.example.mynibmg1.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    public TeacherResponseDto addTeacher(TeacherRequestDto teacherDto) {
        // Check if email already exists
        if (userRepository.findByEmail(teacherDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use: " + teacherDto.getEmail());
        }

        // Check if username already exists
        if (userRepository.findByUserName(teacherDto.getUserName()).isPresent()) {
            throw new RuntimeException("Username already in use: " + teacherDto.getUserName());
        }

        // Find Admin by ID
        Admin admin = adminRepository.findById(teacherDto.getAdminUserId())
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + teacherDto.getAdminUserId()));

        // Create User entity
        User user = new User();
        user.setUserName(teacherDto.getUserName());
        user.setPassword(teacherDto.getPassword());
        user.setEmail(teacherDto.getEmail());
        user.setRole(User.Role.TEACHER);

        // Save User entity
        User savedUser = userRepository.save(user);

        // Create Teacher entity linked to the saved User and Admin
        Teacher teacher = new Teacher();
        teacher.setUser(savedUser);
        teacher.setAdmin(admin);
        teacher.setContact(teacherDto.getContact());
        teacher.setDepartment(teacherDto.getDepartment());

        // Save Teacher entity
        Teacher savedTeacher = teacherRepository.save(teacher);

        return new TeacherResponseDto(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedTeacher.getContact(),
                savedTeacher.getDepartment()
        );
    }


    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> new TeacherResponseDto(
                        teacher.getUser().getUserId(),
                        teacher.getUser().getUserName(),
                        teacher.getUser().getEmail(),
                        teacher.getContact(),
                        teacher.getDepartment()))
                .collect(Collectors.toList());
    }

//    public TeacherResponseDto getTeacherById(Integer id) {
//        Teacher teacher = teacherRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + id));
//
//        return new TeacherResponseDto(
//                teacher.getUser().getUserId(),
//                teacher.getUser().getUserName(),
//                teacher.getUser().getEmail(),
//                teacher.getContact(),
//                teacher.getDepartment());
//    }

    public TeacherResponseDto updateTeacher(Integer id, TeacherRequestDto teacherDto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + id));

        User user = teacher.getUser();
        user.setUserName(teacherDto.getUserName());
        user.setPassword(teacherDto.getPassword());
        user.setEmail(teacherDto.getEmail());
        userRepository.save(user);

        teacher.setContact(teacherDto.getContact());
        teacher.setDepartment(teacherDto.getDepartment());
        teacherRepository.save(teacher);

        return new TeacherResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                teacher.getContact(),
                teacher.getDepartment());
    }

    public void deleteTeacher(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + id));

        teacherRepository.delete(teacher);
        userRepository.delete(teacher.getUser());
    }
}

