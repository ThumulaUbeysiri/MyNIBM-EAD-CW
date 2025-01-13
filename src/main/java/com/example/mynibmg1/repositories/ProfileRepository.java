package com.example.mynibmg1.repositories;

import com.example.mynibmg1.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByStudentUserId(Integer userID);
}

