package com.example.mynibmg1.DTOs;

import java.time.LocalDate;

public class StudentFeedbackRequestDTO {
    private LocalDate date;
    private String content;
    private Integer teacherUserID;
    private Integer studentUserID;

    // Getters and Setters
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
