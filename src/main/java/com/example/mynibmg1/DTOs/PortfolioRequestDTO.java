package com.example.mynibmg1.DTOs;

import java.util.List;

public class PortfolioRequestDTO {
    private List<String> skills;
    private List<String> attachments;
    private Integer userId;

    // Getters and setters...
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
