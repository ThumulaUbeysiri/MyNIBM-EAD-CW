package com.example.mynibmg1.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer announceID;

    private String title;
    private String content;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "eventID", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name = "batch_announcements",
            joinColumns = @JoinColumn(name = "announceID"),
            inverseJoinColumns = @JoinColumn(name = "batchID")
    )
    private Set<Batch> batches = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "student_announcements",
            joinColumns = @JoinColumn(name = "announceID"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<Student> students = new HashSet<>();

    // Getters and setters...

    public Integer getAnnounceID() {
        return announceID;
    }

    public void setAnnounceID(Integer announceID) {
        this.announceID = announceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}

