package ru.otus.questionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.questionnaire.service.exam.Examination;

@SpringBootApplication
public class QuestionnaireApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(QuestionnaireApplication.class, args);
//        Examination examination = context.getBean(Examination.class);
//        examination.startExamination();
    }
}