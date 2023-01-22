package com.example.crmbtf.service;

import com.example.crmbtf.model.Questionnaire;

import java.util.Optional;

public interface QuestionnaireService {
    void createQuestion(String answer0,String answer1,String answer2,String answer3,String question, Long correctAnswerNum);
    Questionnaire findById(Long id);
}
