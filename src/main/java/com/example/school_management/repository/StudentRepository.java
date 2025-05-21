package com.example.school_management.repository;

import com.example.school_management.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findBySchoolId(String id);

    @Query("SELECT student FROM Student student JOIN student.school s WHERE student.id LIKE %:search% " +
            "or student.name LIKE %:search% " +
            "or s.name LIKE %:search% or s.city LIKE %:search% or s.id LIKE %:search% " +
            "or s.email LIKE %:search%")
    Page<Student> findByName(String search, Pageable pageable);
}