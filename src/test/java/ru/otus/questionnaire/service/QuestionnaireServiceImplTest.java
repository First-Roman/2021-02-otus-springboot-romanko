package ru.otus.questionnaire.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.questionnaire.dao.QuestionNotesDAO;
import ru.otus.questionnaire.dto.Question;
import ru.otus.questionnaire.service.message.LocalMessageService;
import ru.otus.questionnaire.service.question.QuestionnaireServiceImpl;
import ru.otus.questionnaire.service.view.InteractiveImpl;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Тестируем сервис")
@SpringBootTest
public class QuestionnaireServiceImplTest {

    private QuestionnaireServiceImpl questionnaireService;
    private LocalMessageService localMessageService;
    private QuestionNotesDAO questionNotesDAO;
    private InteractiveImpl interactive;

    private List<Question> questionList;


    @BeforeEach
    public void setup() {
        questionNotesDAO = Mockito.mock(QuestionNotesDAO.class);
        interactive = Mockito.mock(InteractiveImpl.class);
        localMessageService = Mockito.mock(LocalMessageService.class);
        questionnaireService = new QuestionnaireServiceImpl(questionNotesDAO, interactive, localMessageService);
        questionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestion("My name");
        question.addVariables("Dan");
        question.addVariables("Nik");
        question.addVariables("Roman");
        questionList.add(question);
    }

    @Test
    public void startExaminationTest() {
        Mockito.doReturn(questionList).when(questionNotesDAO).getAllQuestion();
        float fl = Float.valueOf(0);
        assertEquals(fl, questionnaireService.startExamination());
    }

    @Test
    public void startExaminationExceptionTest() {
        assertEquals(NaN, questionnaireService.startExamination());
    }
}
