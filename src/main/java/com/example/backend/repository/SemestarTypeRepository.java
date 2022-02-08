package com.example.backend.repository;

import com.example.backend.model.SemestarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemestarTypeRepository extends JpaRepository<SemestarType,Long> {
    SemestarType findByName(String name);
}
