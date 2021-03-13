package ru.otus.questionnaire.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class LocalConfiguration {

    @Bean
    @ConditionalOnProperty(value = "questionnaire.local", havingValue = "Eng")
    Locale getLocal() {
        return Locale.ENGLISH;
    }

    @Bean
    @ConditionalOnProperty(value = "questionnaire.local", havingValue = "Rus")
    Locale getLocalRus() {
        return Locale.forLanguageTag("ru-Ru");
    }
}
