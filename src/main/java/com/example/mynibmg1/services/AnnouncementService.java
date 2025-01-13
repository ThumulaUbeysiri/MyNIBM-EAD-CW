package com.example.mynibmg1.services;

import com.example.mynibmg1.DTOs.AnnouncementRequestDTO;
import com.example.mynibmg1.DTOs.AnnouncementResponseDTO;
import com.example.mynibmg1.models.*;
import com.example.mynibmg1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Add a new announcement
    public AnnouncementResponseDTO addAnnouncement(AnnouncementRequestDTO requestDTO) {
        Announcement announcement = new Announcement();
        populateAnnouncementFields(announcement, requestDTO);
        announcement = announcementRepository.save(announcement);
        return mapToResponseDTO(announcement);
    }

    // Get announcements by teacher
    public List<AnnouncementResponseDTO> getAnnouncementsByTeacher(Integer teacherID) {
        List<Announcement> announcements = announcementRepository.findByTeacher_UserId(teacherID);
        return announcements.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Update an existing announcement
    public AnnouncementResponseDTO updateAnnouncement(Integer announceID, AnnouncementRequestDTO requestDTO) {
        Announcement announcement = announcementRepository.findById(announceID)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
        populateAnnouncementFields(announcement, requestDTO);
        announcement = announcementRepository.save(announcement);
        return mapToResponseDTO(announcement);
    }

    // Get announcements for a student
    public List<AnnouncementResponseDTO> getAnnouncementsForStudent(Integer studentID) {
        List<Announcement> announcements = announcementRepository.findByStudents_UserId(studentID);
        return announcements.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get announcements for a batch
    public List<AnnouncementResponseDTO> getAnnouncementsForBatch(Integer batchID) {
        List<Announcement> announcements = announcementRepository.findByBatches_BatchID(batchID);
        return announcements.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Helper methods
    private void populateAnnouncementFields(Announcement announcement, AnnouncementRequestDTO requestDTO) {
        announcement.setTitle(requestDTO.getTitle());
        announcement.setContent(requestDTO.getContent());
        announcement.setDate(requestDTO.getDate());

        Event event = eventRepository.findById(requestDTO.getEventID())
                .orElseThrow(() -> new RuntimeException("Event not found"));
        announcement.setEvent(event);

        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherID())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        announcement.setTeacher(teacher);

        Set<Batch> batches = batchRepository.findAllById(requestDTO.getBatchIDs())
                .stream().collect(Collectors.toSet());
        announcement.setBatches(batches);

        Set<Student> students = studentRepository.findAllById(requestDTO.getStudentIDs())
                .stream().collect(Collectors.toSet());
        announcement.setStudents(students);
    }

    private AnnouncementResponseDTO mapToResponseDTO(Announcement announcement) {
        AnnouncementResponseDTO responseDTO = new AnnouncementResponseDTO();
        responseDTO.setAnnounceID(announcement.getAnnounceID());
        responseDTO.setTitle(announcement.getTitle());
        responseDTO.setContent(announcement.getContent());
        responseDTO.setDate(announcement.getDate());
        responseDTO.setEventID(announcement.getEvent().getEventID());
        responseDTO.setTeacherID(announcement.getTeacher().getUserId());
        responseDTO.setBatchIDs(announcement.getBatches().stream()
                .map(Batch::getBatchID).collect(Collectors.toSet()));
        responseDTO.setStudentIDs(announcement.getStudents().stream()
                .map(Student::getUserId).collect(Collectors.toSet()));
        return responseDTO;
    }
}

