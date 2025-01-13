package com.example.mynibmg1.repositories;

import com.example.mynibmg1.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Portfolio findByStudentUserId(Integer userID);
}

