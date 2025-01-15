package com.example.mynibmg1.repositories;

import com.example.mynibmg1.models.BatchFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchFeedbackRepository extends JpaRepository<BatchFeedback, Integer> {
    List<BatchFeedback> findByBatch_BatchID(Integer batchId);
    List<BatchFeedback> findByTeacher_UserId(Integer teacherId);
}

