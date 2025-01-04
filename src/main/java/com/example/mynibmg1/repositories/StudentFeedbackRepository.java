package com.example.mynibmg1.repositories;

import com.example.mynibmg1.models.StudentFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentFeedbackRepository extends JpaRepository<StudentFeedback, Integer> {
    List<StudentFeedback> findByStudent_UserId(Integer studentId);
    List<StudentFeedback> findByTeacher_UserId(Integer teacherId);
}

