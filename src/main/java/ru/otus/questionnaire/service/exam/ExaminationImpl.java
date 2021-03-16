package ru.otus.questionnaire.service.exam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.dto.Student;
import ru.otus.questionnaire.service.message.LocalMessageService;
import ru.otus.questionnaire.service.person.Personalisation;
import ru.otus.questionnaire.service.question.QuestionnaireService;
import ru.otus.questionnaire.service.view.Interactive;


@Service
public class ExaminationImpl implements Examination {

    @Value("${questionnaire.exam.qualityGate}")
    private float qualityGate;

    private final Personalisation personalisation;
    private final QuestionnaireService questionnaireService;
    private final Interactive interactive;
    private final LocalMessageService localMessageService;

    public ExaminationImpl(Personalisation personalisation,
                           QuestionnaireService questionnaireService,
                           Interactive interactive,
                           LocalMessageService localMessageService) {
        this.personalisation = personalisation;
        this.questionnaireService = questionnaireService;
        this.interactive = interactive;
        this.localMessageService = localMessageService;
    }

    @Override
    public void startExamination() {
        Student student = personalisation.getStudent();
        float rating = questionnaireService.startExamination();
        student.setRating(rating);
        statusExamination(rating);
    }

    public void statusExamination(float rating) {
        float qualityGate = (float) 0.1;
        if (rating >= qualityGate) {
            interactive.print(localMessageService.getLocalMessage("message.success"));
        } else {
            interactive.print(localMessageService.getLocalMessage("message.fail"));
        }
    }
}
