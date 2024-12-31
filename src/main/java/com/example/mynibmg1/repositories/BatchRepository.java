package com.example.mynibmg1.repositories;

import com.example.mynibmg1.models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
    List<Batch> findByTeacher_UserId(Integer userId); // Correct method name for finding by teacher's userId
    boolean existsByBatchName(String batchName); // Validation for unique batch name
}
