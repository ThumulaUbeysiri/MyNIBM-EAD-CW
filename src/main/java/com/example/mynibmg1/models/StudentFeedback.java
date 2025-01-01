package com.example.mynibmg1.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "StudentFeedback")
public class StudentFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentFeedbackID;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "teacherUserID", referencedColumnName = "userId", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "studentUserID", referencedColumnName = "userId", nullable = false)
    private Student student;

    // Getters and Setters
    public Integer getStudentFeedbackID() {
        return studentFeedbackID;
    }

    public void setStudentFeedbackID(Integer studentFeedbackID) {
        this.studentFeedbackID = studentFeedbackID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
