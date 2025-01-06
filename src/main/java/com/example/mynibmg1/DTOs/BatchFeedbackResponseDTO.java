package com.example.mynibmg1.DTOs;

import java.time.LocalDate;

public class BatchFeedbackResponseDTO {
    private Integer batchFeedbackID;
    private LocalDate date;
    private String content;
    private Integer teacherUserID;
    private Integer batchID;

    public BatchFeedbackResponseDTO(Integer batchFeedbackID, LocalDate date, String content, Integer userId, Integer batchID) {
    }

    public BatchFeedbackResponseDTO() {

    }

    // Getters and Setters
    public Integer getBatchFeedbackID() {
        return batchFeedbackID;
    }

    public void setBatchFeedbackID(Integer batchFeedbackID) {
        this.batchFeedbackID = batchFeedbackID;
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

    public Integer getBatchID() {
        return batchID;
    }

    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }
}