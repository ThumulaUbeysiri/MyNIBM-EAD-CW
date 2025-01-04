package com.example.mynibmg1.services;

import com.example.mynibmg1.DTOs.StudentFeedbackRequestDTO;
import com.example.mynibmg1.DTOs.StudentFeedbackResponseDTO;
import com.example.mynibmg1.models.Student;
import com.example.mynibmg1.models.StudentFeedback;
import com.example.mynibmg1.models.Teacher;
import com.example.mynibmg1.repositories.StudentFeedbackRepository;
import com.example.mynibmg1.repositories.StudentRepository;
import com.example.mynibmg1.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentFeedbackService {

    @Autowired
    private StudentFeedbackRepository studentFeedbackRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public StudentFeedbackResponseDTO addStudentFeedback(StudentFeedbackRequestDTO requestDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(requestDTO.getTeacherUserID());
        if (teacher.isEmpty()) {
            throw new IllegalArgumentException("Teacher does not exist!");
        }

        Optional<Student> student = studentRepository.findById(requestDTO.getStudentUserID());
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist!");
        }

        StudentFeedback feedback = new StudentFeedback();
        feedback.setDate(requestDTO.getDate());
        feedback.setContent(requestDTO.getContent());
        feedback.setTeacher(teacher.get());
        feedback.setStudent(student.get());

        StudentFeedback savedFeedback = studentFeedbackRepository.save(feedback);
        return mapToResponseDTO(savedFeedback);
    }

    public List<StudentFeedbackResponseDTO> getFeedbacksByStudent(Integer studentId) {
        List<StudentFeedback> feedbacks = studentFeedbackRepository.findByStudent_UserId(studentId);
        return feedbacks.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    public void deleteStudentFeedback(Integer feedbackId) {
        if (!studentFeedbackRepository.existsById(feedbackId)) {
            throw new IllegalArgumentException("StudentFeedback not found!");
        }
        studentFeedbackRepository.deleteById(feedbackId);
    }

    public StudentFeedbackResponseDTO updateStudentFeedback(Integer feedbackId, StudentFeedbackRequestDTO requestDTO) {
        StudentFeedback feedback = studentFeedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalArgumentException("StudentFeedback not found!"));

        Optional<Teacher> teacher = teacherRepository.findById(requestDTO.getTeacherUserID());
        if (teacher.isEmpty()) {
            throw new IllegalArgumentException("Teacher does not exist!");
        }

        Optional<Student> student = studentRepository.findById(requestDTO.getStudentUserID());
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist!");
        }

        feedback.setDate(requestDTO.getDate());
        feedback.setContent(requestDTO.getContent());
        feedback.setTeacher(teacher.get());
        feedback.setStudent(student.get());

        StudentFeedback updatedFeedback = studentFeedbackRepository.save(feedback);
        return mapToResponseDTO(updatedFeedback);
    }


    private StudentFeedbackResponseDTO mapToResponseDTO(StudentFeedback feedback) {
        return new StudentFeedbackResponseDTO(
                feedback.getStudentFeedbackID(),
                feedback.getDate(),
                feedback.getContent(),
                feedback.getTeacher().getUserId(),
                feedback.getStudent().getUserId()
        );
    }
}

