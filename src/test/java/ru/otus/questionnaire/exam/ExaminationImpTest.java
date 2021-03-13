package ru.otus.questionnaire.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.questionnaire.dao.QuestionNotes;
import ru.otus.questionnaire.dto.Question;
import ru.otus.questionnaire.service.exam.Examination;
import ru.otus.questionnaire.service.exam.ExaminationImpl;
import ru.otus.questionnaire.service.message.LocalMessageService;
import ru.otus.questionnaire.service.person.Personalisation;
import ru.otus.questionnaire.service.person.PersonalisationImpl;
import ru.otus.questionnaire.service.question.QuestionnaireService;
import ru.otus.questionnaire.service.question.QuestionnaireServiceImpl;
import ru.otus.questionnaire.service.view.Interactive;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Тестируем сервис экзамена")
@SpringBootTest
public class ExaminationImpTest {

    private Interactive interactive;
    private QuestionNotes questionNotes;
    private Personalisation personalisation;
    private QuestionnaireService questionnaireService;
    private LocalMessageService localMessageService;
    @Spy
    private Examination examination;
    private List<Question> questionList;

    @BeforeEach
    public void setup() {
        questionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestion("My name");
        question.addVariables("Dan");
        question.addVariables("Nik");
        question.addVariables("Roman");
        questionList.add(question);
        interactive = Mockito.mock(Interactive.class);
        questionNotes = Mockito.mock(QuestionNotes.class);
        localMessageService= Mockito.mock(LocalMessageService.class);
        questionnaireService = new QuestionnaireServiceImpl(questionNotes, interactive,localMessageService);
        personalisation = new PersonalisationImpl(interactive,localMessageService);
        examination = new ExaminationImpl(personalisation, questionnaireService, interactive,localMessageService);
    }

    @Test
    public void startExamination() {
        Mockito.doReturn("Roman").doReturn("First").when(interactive).ask();
        Mockito.doReturn(questionList).when(questionNotes).getAllQuestion();
        examination.startExamination();
        Mockito.verify(questionNotes, Mockito.times(1)).getAllQuestion();
    }
}
