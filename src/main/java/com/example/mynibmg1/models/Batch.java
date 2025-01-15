package com.example.mynibmg1.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminUserID", referencedColumnName = "userId", nullable = false)
    private Admin admin; // Admin linked through User's userId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherUserID", referencedColumnName = "userId", nullable = false)
    private Teacher teacher; // Reference Teacher by User's userId (primary key)

    @Column(name = "batchName", nullable = false, length = 100)
    private String batchName;

    @Column(name = "startYear", nullable = false)
    private Integer startYear;

    @Column(name = "endYear", nullable = false)
    private Integer endYear;

    // Getters and setters
    public Integer getBatchID() {
        return batchID;
    }

    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
