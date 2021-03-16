package ru.otus.questionnaire.service.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.questionnaire.service.exam.Examination;
import ru.otus.questionnaire.service.message.LocalMessageService;

@ShellComponent
@AllArgsConstructor
public class ApplicationCommands {

    private final Examination examination;
    private final LocalMessageService localMessageService;

    @ShellMethod(value = "Command to start exam", key = {"start", "старт"})
    @ShellMethodAvailability(value = "isExamAvailability")
    public void start() {
        examination.startExamination();
    }

    private Availability isExamAvailability(){
        return (examination.status())?Availability.available():Availability
                .unavailable(localMessageService
                        .getLocalMessage("message.exam.unavailable"));
    }


}
