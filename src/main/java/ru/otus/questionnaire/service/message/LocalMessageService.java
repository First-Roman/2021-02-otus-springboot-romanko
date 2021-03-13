package ru.otus.questionnaire.service.message;

public interface LocalMessageService {
    String getLocalMessage(String placeHolder);

    String getLocalMessageArg(String placeHolder, String[] arg);
}
