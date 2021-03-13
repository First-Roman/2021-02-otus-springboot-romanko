package ru.otus.questionnaire.service.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.dto.Student;
import ru.otus.questionnaire.service.message.LocalMessageService;
import ru.otus.questionnaire.service.view.Interactive;

@AllArgsConstructor
@Service
public class PersonalisationImpl implements Personalisation {
    private final Interactive interactive;
    private final LocalMessageService localMessageService;

    @Override
    public Student getStudent() {
        askFirstName();
        String firstName = interactive.ask();
        askLastName();
        String lastName = interactive.ask();
        return new Student(firstName, lastName, 0);

    }

    private void askFirstName() {
        String message = localMessageService.getLocalMessage("ask.first.name");
        interactive.print(message);
    }

    private void askLastName() {
        String message = localMessageService.getLocalMessage("ask.last.name");
        interactive.print(message);
    }
}
