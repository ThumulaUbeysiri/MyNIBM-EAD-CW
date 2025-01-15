package com.example.mynibmg1.DTOs;

public class BatchResponseDTO {
    private Integer batchID;
    private String batchName;
    private Integer startYear;
    private Integer endYear;
    private Integer adminUserID;
    private Integer teacherUserID;

    // Getters and Setters
    public Integer getBatchID() {
        return batchID;
    }

    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getAdminUserID() {
        return adminUserID;
    }

    public void setAdminUserID(Integer adminUserID) {
        this.adminUserID = adminUserID;
    }

    public Integer getTeacherUserID() {
        return teacherUserID;
    }

    public void setTeacherUserID(Integer teacherUserID) {
        this.teacherUserID = teacherUserID;
    }
}

