package com.example.mynibmg1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Workshop")
public class Workshop extends Event {
    // No additional fields
}

