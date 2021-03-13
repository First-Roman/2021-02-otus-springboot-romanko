package ru.otus.questionnaire.service.message;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class LocalMessageServiceImpl implements LocalMessageService {
    private final Locale locale;
    private final MessageSource messageSource;

    @Override
    public String getLocalMessage(String placeHolder) {
        return messageSource.getMessage(placeHolder, null, locale);
    }

    @Override
    public String getLocalMessageArg(String placeHolder, String[] arg) {
        return messageSource.getMessage(placeHolder, arg, locale);
    }
}
