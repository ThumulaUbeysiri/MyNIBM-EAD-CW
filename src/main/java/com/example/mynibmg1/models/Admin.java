package com.example.mynibmg1.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    private Integer userId; // Primary key, shared with User table

    @Column(length = 20)
    private String contact; // Admin contact information

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId // Links this userId to the primary key of User
    @JoinColumn(name = "userId") // Ensure the column name matches exactly
    private Users users; // Reference to the User entity

    // Default constructor
    public Admin() {
    }

    // Constructor with all fields
    public Admin(String contact, Users users) {
        this.contact = contact;
        this.users = users;
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

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", contact='" + contact + '\'' +
                ", user=" + users +
                '}';
    }
}
