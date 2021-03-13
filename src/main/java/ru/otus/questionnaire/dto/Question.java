package ru.otus.questionnaire.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Question {
    int rightAnswer;
    private String question;
    private List<String> variables;

    public void addVariables(String variable) {
        if (variables == null) {
            variables = new ArrayList<>();
        }
        variables.add(variable);
    }

}
