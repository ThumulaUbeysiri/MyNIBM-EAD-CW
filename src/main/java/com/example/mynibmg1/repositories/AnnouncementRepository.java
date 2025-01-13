package com.example.mynibmg1.repositories;

import com.example.mynibmg1.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    List<Announcement> findByEvent_EventID(Integer eventID);
    List<Announcement> findByTeacher_UserId(Integer teacherID);

    List<Announcement> findByStudents_UserId(Integer studentID);

    List<Announcement> findByBatches_BatchID(Integer batchID);
}

