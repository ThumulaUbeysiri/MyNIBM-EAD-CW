package com.example.mynibmg1.DTOs;

import java.time.LocalDate;
import java.util.Set;

public class AnnouncementRequestDTO {
    private String title;
    private String content;
    private LocalDate date;
    private Integer eventID;
    private Integer teacherID;
    private Set<Integer> batchIDs;
    private Set<Integer> studentIDs;

    // Getters and setters...

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

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
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

