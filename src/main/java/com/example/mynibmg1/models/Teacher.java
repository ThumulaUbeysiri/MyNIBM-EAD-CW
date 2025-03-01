package com.example.mynibmg1.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    private Integer userId; // Primary key, shared with User table

    @Column(length = 20)
    private String contact; // Contact information of the teacher

    @Column(length = 100, nullable = false)
    private String department; // Department the teacher belongs to

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId // Maps the userId to the User table's primary key
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users; // Reference to User entity

    @ManyToOne
    @JoinColumn(name = "adminUserID", referencedColumnName = "userId")
    private Admin admin; // Reference to Admin entity

    // Default constructor
    public Teacher() {
    }

    // Constructor with all fields
    public Teacher(String contact, String department, Users users, Admin admin) {
        this.contact = contact;
        this.department = department;
        this.users = users;
        this.admin = admin;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userId=" + userId +
                ", contact='" + contact + '\'' +
                ", department='" + department + '\'' +
                ", user=" + users +
                ", admin=" + admin +
                '}';
    }
}
