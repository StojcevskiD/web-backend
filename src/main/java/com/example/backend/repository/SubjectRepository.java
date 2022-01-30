package com.example.backend.repository;

import com.example.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
