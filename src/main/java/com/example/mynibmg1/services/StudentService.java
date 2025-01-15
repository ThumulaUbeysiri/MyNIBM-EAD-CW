package com.example.mynibmg1.services;

import com.example.mynibmg1.DTOs.StudentRequestDTO;
import com.example.mynibmg1.DTOs.StudentResponseDTO;
import com.example.mynibmg1.models.Admin;
import com.example.mynibmg1.models.Batch;
import com.example.mynibmg1.models.Student;
import com.example.mynibmg1.models.Users;
import com.example.mynibmg1.repositories.AdminRepository;
import com.example.mynibmg1.repositories.BatchRepository;
import com.example.mynibmg1.repositories.StudentRepository;
import com.example.mynibmg1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BatchRepository batchRepository;

    public StudentService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public StudentResponseDTO addStudent(StudentRequestDTO requestDTO) {
        // Validate unique email and username
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use: " + requestDTO.getEmail());
        }

        if (userRepository.findByUserName(requestDTO.getUserName()).isPresent()) {
            throw new RuntimeException("Username already in use: " + requestDTO.getUserName());
        }

        // Find Admin by ID
        Admin admin = adminRepository.findById(requestDTO.getAdminUserID())
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + requestDTO.getAdminUserID()));

        // Find Batch by ID
        Batch batch = batchRepository.findById(requestDTO.getBatchID())
                .orElseThrow(() -> new RuntimeException("Batch not found with ID: " + requestDTO.getBatchID()));

        // Create and save User entity
        Users users = new Users();
        users.setUserName(requestDTO.getUserName());
        users.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        users.setEmail(requestDTO.getEmail());
        users.setRole(Users.Role.STUDENT);

        Users savedUsers = userRepository.save(users);

        // Create and save Student entity
        Student student = new Student();
        student.setUser(savedUsers);
        student.setAdmin(admin);
        student.setBatch(batch);
        student.setEnrollmentYear(requestDTO.getEnrollmentYear());

        Student savedStudent = studentRepository.save(student);

        return mapToResponseDTO(savedStudent);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

//    public StudentResponseDTO getStudentById(Integer userId) {
//        Student student = studentRepository.findByUser_UserId(userId)
//                .orElseThrow(() -> new RuntimeException("Student not found with User ID: " + userId));
//        return mapToResponseDTO(student);
//    }

    public List<StudentResponseDTO> getStudentsByAdminUserId(Integer adminUserId) {
        // Find Admin by ID
        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(() -> new RuntimeException("Admin not found with User ID: " + adminUserId));

        // Fetch students linked to this Admin
        List<Student> students = studentRepository.findByAdmin_UserId(adminUserId);

        // Map students to response DTOs
        return students.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }


    public StudentResponseDTO updateStudent(Integer userId, StudentRequestDTO requestDTO) {
        // Find Student by User ID
        Student student = studentRepository.findByUsers_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Student not found with User ID: " + userId));

        // Update User details
        Users users = student.getUser();
        users.setUserName(requestDTO.getUserName());
        users.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        users.setEmail(requestDTO.getEmail());
        userRepository.save(users);

        // Update Admin and Batch relationships
        Admin admin = adminRepository.findById(requestDTO.getAdminUserID())
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + requestDTO.getAdminUserID()));

        Batch batch = batchRepository.findById(requestDTO.getBatchID())
                .orElseThrow(() -> new RuntimeException("Batch not found with ID: " + requestDTO.getBatchID()));

        student.setAdmin(admin);
        student.setBatch(batch);
        student.setEnrollmentYear(requestDTO.getEnrollmentYear());

        Student updatedStudent = studentRepository.save(student);

        return mapToResponseDTO(updatedStudent);
    }

    public void deleteStudent(Integer userId) {
        Student student = studentRepository.findByUsers_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Student not found with User ID: " + userId));

        studentRepository.delete(student);
        userRepository.delete(student.getUser());
    }

    private StudentResponseDTO mapToResponseDTO(Student student) {
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setUserId(student.getUser().getUserId());
        responseDTO.setUserName(student.getUser().getUserName());
        responseDTO.setEmail(student.getUser().getEmail());
        responseDTO.setRole(student.getUser().getRole().name());
        responseDTO.setAdminUserID(student.getAdmin().getUserId());
        responseDTO.setBatchID(student.getBatch().getBatchID());
        responseDTO.setEnrollmentYear(student.getEnrollmentYear());
        return responseDTO;
    }
}
