package com.example.mynibmg1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Interview")
public class Interview extends Event {
    @Column(nullable = false, length = 50)
    private String type; // e.g., Technical, HR

    // Getters and setters...

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
