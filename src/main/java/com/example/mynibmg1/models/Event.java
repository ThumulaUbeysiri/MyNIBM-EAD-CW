package com.example.mynibmg1.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Event")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventID;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 150)
    private String venue;

    @Column(length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacherUserID", nullable = false)
    private Teacher teacher; // Teacher who scheduled the event

    @ManyToMany
    @JoinTable(
            name = "StudentEvent",
            joinColumns = @JoinColumn(name = "eventID"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "BatchEvent",
            joinColumns = @JoinColumn(name = "eventID"),
            inverseJoinColumns = @JoinColumn(name = "batchID")
    )
    private Set<Batch> batches = new HashSet<>();

    // Getters and Setters
    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }
}
