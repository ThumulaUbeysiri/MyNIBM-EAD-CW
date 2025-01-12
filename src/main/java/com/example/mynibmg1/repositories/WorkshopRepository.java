package com.example.mynibmg1.repositories;


import com.example.mynibmg1.models.Batch;
import com.example.mynibmg1.models.Student;
import com.example.mynibmg1.models.Teacher;
import com.example.mynibmg1.models.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

    // Find Workshops by Teacher
    List<Workshop> findByTeacher(Teacher teacher);

    // Find Workshops by Batch
    List<Workshop> findByBatchesContains(Batch batch);

    // Find Workshops by Student
    List<Workshop> findByStudentsContains(Student student);
}

