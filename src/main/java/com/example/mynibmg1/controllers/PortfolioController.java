package com.example.mynibmg1.controllers;

import com.example.mynibmg1.DTOs.PortfolioRequestDTO;
import com.example.mynibmg1.DTOs.PortfolioResponseDTO;
import com.example.mynibmg1.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    // Add a new portfolio
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<PortfolioResponseDTO> addPortfolio(@RequestBody PortfolioRequestDTO requestDTO) {
        PortfolioResponseDTO responseDTO = portfolioService.addPortfolio(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // View portfolio by student ID
    @GetMapping("/get/{userID}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<PortfolioResponseDTO> getPortfolioByStudentID(@PathVariable Integer userID) {
        PortfolioResponseDTO responseDTO = portfolioService.getPortfolioByStudentID(userID);
        return ResponseEntity.ok(responseDTO);
    }

    // Update portfolio
    @PutMapping("/update/{userID}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<PortfolioResponseDTO> updatePortfolio(
            @PathVariable Integer userID,
            @RequestBody PortfolioRequestDTO requestDTO) {
        PortfolioResponseDTO responseDTO = portfolioService.updatePortfolio(userID, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}

