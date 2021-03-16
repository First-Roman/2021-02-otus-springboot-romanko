package ru.otus.questionnaire.service.exam;

public interface Examination {
    void startExamination();

    void getAssessment(float rating);
    
    boolean status();
}
