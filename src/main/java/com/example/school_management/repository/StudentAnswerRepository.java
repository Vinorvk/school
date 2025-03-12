package com.example.school_management.repository;

import com.example.school_management.entity.Studentanswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<Studentanswer, String> {
    List<Studentanswer> findByStudentId(String studentid);
}