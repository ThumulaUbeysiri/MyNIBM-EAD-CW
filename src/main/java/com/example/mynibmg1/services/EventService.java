package com.example.mynibmg1.services;

import com.example.mynibmg1.DTOs.EventRequestDTO;
import com.example.mynibmg1.DTOs.EventResponseDTO;
import com.example.mynibmg1.DTOs.InterviewRequestDTO;
import com.example.mynibmg1.models.*;
import com.example.mynibmg1.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private StudentRepository studentRepository;

    // ** Create Interview for Batch **
    public EventResponseDTO createInterviewForBatch(InterviewRequestDTO requestDTO, Integer batchId) {
        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        // Create and populate the Interview object
        Interview interview = new Interview();
        interview.setTitle(requestDTO.getTitle());
        interview.setTime(requestDTO.getTime());
        interview.setDate(requestDTO.getDate());
        interview.setVenue(requestDTO.getVenue());
        interview.setDescription(requestDTO.getDescription());
        interview.setType(requestDTO.getType()); // Set type from InterviewRequestDTO
        interview.setTeacher(teacher);

        // Associate the batch with the interview
        interview.getBatches().add(batch);

        // Save and return the response DTO
        Interview savedInterview = interviewRepository.save(interview);
        return mapToResponseDTO(savedInterview);
    }

    // ** Create Interview for Student **
    public EventResponseDTO createInterviewForStudent(InterviewRequestDTO requestDTO, Integer studentId) {
        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Create and populate the Interview object
        Interview interview = new Interview();
        interview.setTitle(requestDTO.getTitle());
        interview.setTime(requestDTO.getTime());
        interview.setDate(requestDTO.getDate());
        interview.setVenue(requestDTO.getVenue());
        interview.setDescription(requestDTO.getDescription());
        interview.setType(requestDTO.getType()); // Set type from InterviewRequestDTO
        interview.setTeacher(teacher);

        // Associate the student with the interview
        interview.getStudents().add(student);

        // Save and return the response DTO
        Interview savedInterview = interviewRepository.save(interview);
        return mapToResponseDTO(savedInterview);
    }


    // ** Create Workshop for Batch **
    public EventResponseDTO createWorkshopForBatch(EventRequestDTO requestDTO, Integer batchId) {
        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        Workshop workshop = new Workshop();
        workshop.setTitle(requestDTO.getTitle());
        workshop.setTime(requestDTO.getTime());
        workshop.setDate(requestDTO.getDate());
        workshop.setVenue(requestDTO.getVenue());
        workshop.setDescription(requestDTO.getDescription());
        workshop.setTeacher(teacher);

        // Associate with the batch
        workshop.getBatches().add(batch);

        Workshop savedWorkshop = workshopRepository.save(workshop);

        return mapToResponseDTO(savedWorkshop);
    }

    // ** Create Workshop for Student **
    public EventResponseDTO createWorkshopForStudent(EventRequestDTO requestDTO, Integer studentId) {
        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Workshop workshop = new Workshop();
        workshop.setTitle(requestDTO.getTitle());
        workshop.setTime(requestDTO.getTime());
        workshop.setDate(requestDTO.getDate());
        workshop.setVenue(requestDTO.getVenue());
        workshop.setDescription(requestDTO.getDescription());
        workshop.setTeacher(teacher);

        // Associate with the student
        workshop.getStudents().add(student);

        Workshop savedWorkshop = workshopRepository.save(workshop);

        return mapToResponseDTO(savedWorkshop);
    }

    public EventResponseDTO updateInterviewForBatch(Integer interviewId, Integer batchId, InterviewRequestDTO requestDTO) {
        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));


        // Retrieve the Interview entity
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        // Retrieve the Batch entity
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        // Update fields of the Interview from the DTO
        interview.setTitle(requestDTO.getTitle());
        interview.setTime(requestDTO.getTime());
        interview.setDate(requestDTO.getDate());
        interview.setVenue(requestDTO.getVenue());
        interview.setDescription(requestDTO.getDescription());
        interview.setType(requestDTO.getType()); // Set type from InterviewRequestDTO
        interview.setTeacher(teacher);

        // Add the Batch to the interview's batches list
        interview.getBatches().add(batch);

        // Save the updated interview to the database
        interviewRepository.save(interview);

        // Return the updated interview as a response DTO
        return mapToResponseDTO(interview);
    }



    // ** Update Interview for Student **
    public EventResponseDTO updateInterviewForStudent(Integer interviewId, Integer studentId, InterviewRequestDTO requestDTO) {

        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));


        // Retrieve the Interview entity
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        // Retrieve the Student entity
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Update fields of the Interview from the DTO
        interview.setTitle(requestDTO.getTitle());
        interview.setTime(requestDTO.getTime());
        interview.setDate(requestDTO.getDate());
        interview.setVenue(requestDTO.getVenue());
        interview.setDescription(requestDTO.getDescription());
        interview.setType(requestDTO.getType()); // Set type from InterviewRequestDTO
        interview.setTeacher(teacher);


        // Add the Student to the interview's students list
        interview.getStudents().add(student);

        // Save the updated interview to the database
        interviewRepository.save(interview);

        // Return the updated interview as a response DTO
        return mapToResponseDTO(interview);
    }


    // ** Update Workshop for Batch **

    public EventResponseDTO updateWorkshopForBatch(Integer workshopId, Integer batchId, EventRequestDTO requestDTO) {
        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));


        // Retrieve the Workshop entity
        Workshop workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new RuntimeException("Workshop not found"));

        // Retrieve the Batch entity
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        // Update fields of the Workshop from the DTO
        workshop.setTitle(requestDTO.getTitle());
        workshop.setTime(requestDTO.getTime());
        workshop.setDate(requestDTO.getDate());
        workshop.setVenue(requestDTO.getVenue());
        workshop.setDescription(requestDTO.getDescription());
        workshop.setTeacher(teacher);

        // Add the Batch to the workshop's batches list
        workshop.getBatches().add(batch);

        // Save the updated workshop to the database
        workshopRepository.save(workshop);

        // Return the updated workshop as a response DTO
        return mapToResponseDTO(workshop);
    }

    // ** Update Workshop for Student **
    public EventResponseDTO updateWorkshopForStudent(Integer workshopId, Integer studentId, EventRequestDTO requestDTO) {
        Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));


        // Retrieve the Workshop entity
        Workshop workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new RuntimeException("Workshop not found"));

        // Retrieve the Student entity
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Update fields of the Workshop from the DTO
        workshop.setTitle(requestDTO.getTitle());
        workshop.setTime(requestDTO.getTime());
        workshop.setDate(requestDTO.getDate());
        workshop.setVenue(requestDTO.getVenue());
        workshop.setDescription(requestDTO.getDescription());
        workshop.setTeacher(teacher);

        // Add the Student to the workshop's students list
        workshop.getStudents().add(student);

        // Save the updated workshop to the database
        workshopRepository.save(workshop);

        // Return the updated workshop as a response DTO
        return mapToResponseDTO(workshop);
    }



    // ---- Read Methods ----

    // Get All Interviews Created by Teacher
    public List<EventResponseDTO> getInterviewsByTeacher(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        List<Interview> interviews = interviewRepository.findByTeacher(teacher);

        return interviews.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get All Workshops Created by Teacher
    public List<EventResponseDTO> getWorkshopsByTeacher(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        List<Workshop> workshops = workshopRepository.findByTeacher(teacher);

        return workshops.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get All Events for a Batch
    public List<EventResponseDTO> getEventsForBatch(Integer batchId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        List<Event> events = eventRepository.findByBatchesContains(batch);

        return events.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get All Events for a Student
    public List<EventResponseDTO> getEventsForStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Event> events = eventRepository.findByStudentsContains(student);

        return events.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // ---- Delete Methods ----

    // Delete Interview for Batch
    public void deleteInterviewForBatch(Integer interviewId, Integer batchId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        interview.getBatches().remove(batch);
        interviewRepository.save(interview);

        if (interview.getBatches().isEmpty() && interview.getStudents().isEmpty()) {
            interviewRepository.deleteById(interviewId);
        }
    }

    // Delete Interview for Student
    public void deleteInterviewForStudent(Integer interviewId, Integer studentId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        interview.getStudents().remove(student);
        interviewRepository.save(interview);

        if (interview.getStudents().isEmpty() && interview.getBatches().isEmpty()) {
            interviewRepository.deleteById(interviewId);
        }
    }

    // Delete Workshop for Batch
    public void deleteWorkshopForBatch(Integer workshopId, Integer batchId) {
        Workshop workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new RuntimeException("Workshop not found"));

        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        workshop.getBatches().remove(batch);
        workshopRepository.save(workshop);

        if (workshop.getBatches().isEmpty() && workshop.getStudents().isEmpty()) {
            workshopRepository.deleteById(workshopId);
        }
    }

    // Delete Workshop for Student
    public void deleteWorkshopForStudent(Integer workshopId, Integer studentId) {
        Workshop workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new RuntimeException("Workshop not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        workshop.getStudents().remove(student);
        workshopRepository.save(workshop);

        if (workshop.getBatches().isEmpty() && workshop.getStudents().isEmpty()) {
            workshopRepository.deleteById(workshopId);
        }
    }

    // ---- Additional Read Methods ----

    // Get All Interviews for a Specific Student
    public List<EventResponseDTO> getInterviewsByStudentId(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Interview> interviews = interviewRepository.findByStudentsContains(student);

        return interviews.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get All Workshops for a Specific Student
    public List<EventResponseDTO> getWorkshopsByStudentId(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Workshop> workshops = workshopRepository.findByStudentsContains(student);

        return workshops.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get All Interviews for a Specific Batch
    public List<EventResponseDTO> getInterviewsByBatchId(Integer batchId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        List<Interview> interviews = interviewRepository.findByBatchesContains(batch);

        return interviews.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get All Workshops for a Specific Batch
    public List<EventResponseDTO> getWorkshopsByBatchId(Integer batchId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        List<Workshop> workshops = workshopRepository.findByBatchesContains(batch);

        return workshops.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }


    // Utility: Map Event to Response DTO
    private EventResponseDTO mapToResponseDTO(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setEventId(event.getEventID());
        dto.setTitle(event.getTitle());
        dto.setTime(event.getTime());
        dto.setDate(event.getDate());
        dto.setVenue(event.getVenue());
        dto.setDescription(event.getDescription());
        dto.setTeacherUserId(event.getTeacher().getUserId()); // Use teacherUserId
        return dto;
    }

}

