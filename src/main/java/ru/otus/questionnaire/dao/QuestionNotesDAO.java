package ru.otus.questionnaire.dao;

import au.com.bytecode.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Repository;
import ru.otus.questionnaire.dto.Question;
import ru.otus.questionnaire.service.message.LocalMessageService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Repository
public class QuestionNotesDAO implements QuestionNotes {
    private final Converter converter;
    private final LocalMessageService localMessageService;


    @Override
    public List<Question> getAllQuestion() {
        String name = localMessageService.getLocalMessage("questionnaire.file.name");
        List<String[]> stringList = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(name)), ';', '"', 0);
            String[] line;
            while ((line = reader.readNext()) != null) {
                stringList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<Question>) converter.convert(stringList);
    }
}
