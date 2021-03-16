package ru.otus.questionnaire.convertor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.questionnaire.dto.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестируем конвертор")
@SpringBootTest
public class ConverterListArrayStringToListQuestionTest {

    private ConverterListArrayStringToListQuestion converterListArrayStringToListQuestion = new ConverterListArrayStringToListQuestion();

    @Test
    void convert() {
        String[] strings = new String[6];
        strings[0] = "Quest";
        strings[1] = "answer";
        strings[2] = "answer";
        strings[3] = "answer";
        strings[4] = "answer";
        strings[5] = "=2";
        List<String[]> stringList = new ArrayList<>();
        stringList.add(strings);
        List<Question> questions = converterListArrayStringToListQuestion.convert(stringList);
        assertEquals("Quest", questions.get(0).getQuestion());

    }


}
