package ru.otus.questionnaire.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Student {
    private String firstName;
    private String lastName;
    private float rating;
}
