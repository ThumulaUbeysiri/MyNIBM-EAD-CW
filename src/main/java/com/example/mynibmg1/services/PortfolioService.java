package com.example.mynibmg1.services;

import com.example.mynibmg1.DTOs.PortfolioRequestDTO;
import com.example.mynibmg1.DTOs.PortfolioResponseDTO;
import com.example.mynibmg1.models.Portfolio;
import com.example.mynibmg1.models.Student;
import com.example.mynibmg1.repositories.PortfolioRepository;
import com.example.mynibmg1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Add a new portfolio
    public PortfolioResponseDTO addPortfolio(PortfolioRequestDTO requestDTO) {
        // Check if the student exists
        Student student = studentRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Student not found for ID: " + requestDTO.getUserId()));

        // Ensure the student doesn't already have a portfolio
        if (portfolioRepository.findByStudentUserId(requestDTO.getUserId()) != null) {
            throw new RuntimeException("Portfolio already exists for student ID: " + requestDTO.getUserId());
        }

        Portfolio portfolio = new Portfolio();
        portfolio.setCreatedDate(LocalDate.now());
        portfolio.setLastUpdated(LocalDate.now());
        portfolio.setSkills(requestDTO.getSkills());
        portfolio.setAttachments(requestDTO.getAttachments());
        portfolio.setStudent(student);

        portfolio = portfolioRepository.save(portfolio);
        return mapToResponseDTO(portfolio);
    }

    // View portfolio by student ID
    public PortfolioResponseDTO getPortfolioByStudentID(Integer userID) {
        Portfolio portfolio = portfolioRepository.findByStudentUserId(userID);
        if (portfolio == null) {
            throw new RuntimeException("Portfolio not found for student ID: " + userID);
        }
        return mapToResponseDTO(portfolio);
    }

    // Update an existing portfolio
    public PortfolioResponseDTO updatePortfolio(Integer userID, PortfolioRequestDTO requestDTO) {
        Portfolio portfolio = portfolioRepository.findByStudentUserId(userID);
        if (portfolio == null) {
            throw new RuntimeException("Portfolio not found for student ID: " + userID);
        }

        portfolio.setLastUpdated(LocalDate.now());
        portfolio.setSkills(requestDTO.getSkills());
        portfolio.setAttachments(requestDTO.getAttachments());

        portfolio = portfolioRepository.save(portfolio);
        return mapToResponseDTO(portfolio);
    }

    // Map Portfolio to PortfolioResponseDTO
    private PortfolioResponseDTO mapToResponseDTO(Portfolio portfolio) {
        PortfolioResponseDTO responseDTO = new PortfolioResponseDTO();
        responseDTO.setPortfolioID(portfolio.getPortfolioID());
        responseDTO.setCreatedDate(portfolio.getCreatedDate());
        responseDTO.setLastUpdated(portfolio.getLastUpdated());
        responseDTO.setSkills(portfolio.getSkills());
        responseDTO.setAttachments(portfolio.getAttachments());
        responseDTO.setUserId(portfolio.getStudent().getUserId());
        return responseDTO;
    }
}
