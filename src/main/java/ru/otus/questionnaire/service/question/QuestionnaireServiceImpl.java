package ru.otus.questionnaire.service.question;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.dao.QuestionNotes;
import ru.otus.questionnaire.dto.Question;
import ru.otus.questionnaire.service.message.LocalMessageService;
import ru.otus.questionnaire.service.view.Interactive;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

@AllArgsConstructor
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionNotes questionNotes;
    private final Interactive interactive;
    private final LocalMessageService localMessageService;

    @Override
    public float startExamination() {
        AtomicInteger rating = new AtomicInteger();
        List<Question> questions = getQuestions();
        questions.forEach(question -> {
            printQuestion(question);
            String answer = getAnswer();
            boolean bool = verify(question, answer);
            answerMessage(answer, bool);
            int i = (bool) ? 1 : 0;
            rating.set(rating.get() + i);
        });
        interactive.print(localMessageService.getLocalMessage("message.end.test"));
        return Float.intBitsToFloat(rating.get()) / Float.intBitsToFloat(questions.size());
    }

    private List<Question> getQuestions() {
        return questionNotes.getAllQuestion();
    }

    private void printQuestion(Question question) {
        int count = 1;
        interactive.print(localMessageService.getLocalMessage("message.separator"));
        interactive.print(question.getQuestion());
        for (String variable : question.getVariables()) {
            interactive.print(count + ") " + variable);
            count++;
        }
    }

    private String getAnswer() {
        interactive.print(localMessageService.getLocalMessage("ask.you.answer"));
        return interactive.ask();
    }

    private boolean verify(Question question, String answer) {
        try {
            int num = parseInt(answer);
            return question.getRightAnswer() == num;
        } catch (Exception e) {
            return false;
        }

    }

    private void answerMessage(String answer, boolean bool) {
        String b = localMessageService.getLocalMessage("message." + String.valueOf(bool));
        String[] arg = {answer, b};
        String m = localMessageService.getLocalMessageArg("message.answer", arg);
        interactive.print(m);
    }

}
