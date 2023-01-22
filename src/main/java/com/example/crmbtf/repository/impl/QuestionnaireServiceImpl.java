package com.example.crmbtf.repository.impl;

import com.example.crmbtf.model.Questionnaire;
import com.example.crmbtf.repository.QuestionRepository;
import com.example.crmbtf.service.QuestionnaireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionRepository questionRepository;

    public QuestionnaireServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void createQuestion(String answer0,String answer1,String answer2,String answer3,String question,Long correctAnswerNum) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestion(question);
        questionnaire.setAnswer0(answer0);
        questionnaire.setAnswer1(answer1);
        questionnaire.setAnswer2(answer2);
        questionnaire.setAnswer3(answer3);
        questionnaire.setCorrectAnswerNum(correctAnswerNum);
        questionRepository.save(questionnaire);
        log.info("Question created");
    }

    @Override
    public Questionnaire findById(Long id) {
        Optional<Questionnaire> byId = questionRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new RuntimeException("Questionnaire not found");
    }
}
