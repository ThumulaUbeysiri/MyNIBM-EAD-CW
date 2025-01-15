package com.example.mynibmg1.repositories;

import com.example.mynibmg1.models.Batch;
import com.example.mynibmg1.models.Event;
import com.example.mynibmg1.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    // Find Events by Batch
    List<Event> findByBatchesContains(Batch batch);

    // Find Events by Student
    List<Event> findByStudentsContains(Student student);
}

