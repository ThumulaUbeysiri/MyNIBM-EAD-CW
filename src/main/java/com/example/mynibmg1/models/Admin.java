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
    @MapsId // Maps the userId field to the primary key of User
    @JoinColumn(name = "user_Id", referencedColumnName = "userId")
    private User user; // Reference to the User entity

    // Default constructor
    public Admin() {
    }

    // Constructor with all fields
    public Admin(String contact, User user) {
        this.contact = contact;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", contact='" + contact + '\'' +
                ", user=" + user +
                '}';
    }
}
