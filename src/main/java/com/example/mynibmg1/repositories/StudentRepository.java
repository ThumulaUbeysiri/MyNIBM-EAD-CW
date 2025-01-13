package com.example.mynibmg1.repositories;


import com.example.mynibmg1.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByAdmin_UserId(Integer adminID);

    Optional<Student> findByUsers_UserId(Integer userId);

}
