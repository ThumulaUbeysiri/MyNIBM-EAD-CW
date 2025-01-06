package com.example.mynibmg1.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "BatchFeedback")
public class BatchFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchFeedbackID;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "teacherUserID", referencedColumnName = "userId", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "batchID", referencedColumnName = "batchID", nullable = false)
    private Batch batch;

    // Getters and Setters
    public Integer getBatchFeedbackID() {
        return batchFeedbackID;
    }

    public void setBatchFeedbackID(Integer batchFeedbackID) {
        this.batchFeedbackID = batchFeedbackID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
}
