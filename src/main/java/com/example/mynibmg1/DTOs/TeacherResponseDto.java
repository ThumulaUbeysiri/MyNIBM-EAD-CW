package com.example.mynibmg1.DTOs;

public class TeacherResponseDto {
    private Integer userId;
    private String userName;
    private String email;
    private String contact;
    private String department;

    // Constructor
    public TeacherResponseDto(Integer userId, String userName, String email, String contact, String department) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.contact = contact;
        this.department = department;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
