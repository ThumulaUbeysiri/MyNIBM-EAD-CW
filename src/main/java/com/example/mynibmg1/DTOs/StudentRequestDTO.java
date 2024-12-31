package com.example.mynibmg1.DTOs;

public class StudentRequestDTO {
    //private Integer userId; // Optional for creation; required for updates
    private String userName;
    private String password;
    private String email;
    private Integer adminUserID;
    private Integer batchID;
    private Integer enrollmentYear;

    // Getters and Setters
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAdminUserID() {
        return adminUserID;
    }

    public void setAdminUserID(Integer adminUserID) {
        this.adminUserID = adminUserID;
    }

    public Integer getBatchID() {
        return batchID;
    }

    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }
}
