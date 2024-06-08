package com.example.sms3.institution;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Institution findByInstitutionName(String institutionName);

    List<Institution> findByInstitutionNameContaining(String searchTerm);
}