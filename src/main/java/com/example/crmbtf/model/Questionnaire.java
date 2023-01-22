package com.example.crmbtf.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

@Table(name = "t_questionnaire")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "answer0")
    private String answer0;

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2")
    private String answer2;

    @Column(name = "answer3")
    private String answer3;

    @Column(name = "question")
    private String question;

    @Column(name = "correctAnswerNum")
    private Long correctAnswerNum;

    public Questionnaire(Long id,
                         String answer0,
                         String answer1,
                         String answer2,
                         String answer3,
                         String question,
                         Long correctAnswerNum) {
        this.id = id;
        this.answer0 = answer0;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.question = question;
        this.correctAnswerNum = correctAnswerNum;

    }
}
