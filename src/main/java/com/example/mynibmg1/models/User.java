package com.example.mynibmg1.models;
import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId; // Primary key for User table

    @Column(unique = true, nullable = false)
    private String userName; // Unique username

    @Column(unique = true, nullable = false)
    private String email; // Unique email

    @Column(nullable = false)
    private String password; // Password for authentication

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Role of the user (e.g., ADMIN, TEACHER, STUDENT)

    // Enum for roles
    public enum Role {
        ADMIN, TEACHER, STUDENT
    }

    // Default constructor
    public User() {
    }

    // Constructor with all fields
    public User(String userName, String email, String password, Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}

