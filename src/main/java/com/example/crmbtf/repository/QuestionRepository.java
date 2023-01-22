package com.example.crmbtf.repository;

import com.example.crmbtf.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QuestionRepository extends JpaRepository<Questionnaire, Long> {
    Optional<Questionnaire> findById(Long id);
}
