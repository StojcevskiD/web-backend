package com.example.backend.repository;


import com.example.backend.model.Exam_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Exam_TypeRepository extends JpaRepository<Exam_Type, Long> {

}
