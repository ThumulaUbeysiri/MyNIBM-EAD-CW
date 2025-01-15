package com.example.mynibmg1.DTOs;

import java.time.LocalDate;
import java.util.Set;

public class AnnouncementResponseDTO {
    private Integer announceID;
    private String title;
    private String content;
    private LocalDate date;
    private Integer eventID;
    private Integer teacherID;
    private Set<Integer> batchIDs;
    private Set<Integer> studentIDs;

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

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Set<Integer> getBatchIDs() {
        return batchIDs;
    }

    public void setBatchIDs(Set<Integer> batchIDs) {
        this.batchIDs = batchIDs;
    }

    public Set<Integer> getStudentIDs() {
        return studentIDs;
    }

    public void setStudentIDs(Set<Integer> studentIDs) {
        this.studentIDs = studentIDs;
    }
}

