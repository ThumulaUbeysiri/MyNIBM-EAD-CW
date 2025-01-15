package com.example.mynibmg1.DTOs;

import java.time.LocalDate;

public class StudentFeedbackResponseDTO {
    private Integer studentFeedbackID;
    private LocalDate date;
    private String content;
    private Integer teacherUserID;
    private Integer studentUserID;

    public StudentFeedbackResponseDTO(Integer studentFeedbackID, LocalDate date, String content, Integer teacherUserID, Integer studentUserID) {
        this.studentFeedbackID = studentFeedbackID;
        this.date = date;
        this.content = content;
        this.teacherUserID = teacherUserID;
        this.studentUserID = studentUserID;
    }

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

    public Integer getTeacherUserID() {
        return teacherUserID;
    }

    public void setTeacherUserID(Integer teacherUserID) {
        this.teacherUserID = teacherUserID;
    }

    public Integer getStudentUserID() {
        return studentUserID;
    }

    public void setStudentUserID(Integer studentUserID) {
        this.studentUserID = studentUserID;
    }
}
