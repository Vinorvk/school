package com.example.school_management.repository;

import com.example.school_management.entity.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, String> {
    @Query("SELECT questions FROM Questions questions JOIN questions.tutor t " +
            "WHERE questions.id LIKE %:search% or questions.question LIKE %:search% " +
            "or t.id LIKE %:search% or t.tutor_name LIKE %:search%")
    Page<Questions> findBYQuestions(String search, Pageable pageable);
}