package com.example.mynibmg1.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    private Integer userId; // Primary key, shared with User table

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminUserID", referencedColumnName = "userId", nullable = false)
    private Admin admin; // Reference to Admin who created the student

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batchID", referencedColumnName = "batchID", nullable = false)
    private Batch batch; // Reference to Batch the student belongs to

    @Column(name = "enrollmentYear", nullable = false)
    private Integer enrollmentYear;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users; // Reference to User entity

    // Default constructor
    public Student() {
    }

    // Constructor with all fields
    public Student(Admin admin, Batch batch, Integer enrollmentYear, Users users) {
        this.admin = admin;
        this.batch = batch;
        this.enrollmentYear = enrollmentYear;
        this.users = users;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }
}
