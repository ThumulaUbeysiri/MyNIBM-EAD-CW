package com.example.mynibmg1.repositories;

import com.example.mynibmg1.models.Batch;
import com.example.mynibmg1.models.Interview;
import com.example.mynibmg1.models.Student;
import com.example.mynibmg1.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

    // Find Interviews by Teacher
    List<Interview> findByTeacher(Teacher teacher);

    // Find Interviews by Batch
    List<Interview> findByBatchesContains(Batch batch);

    // Find Interviews by Student
    List<Interview> findByStudentsContains(Student student);
}

