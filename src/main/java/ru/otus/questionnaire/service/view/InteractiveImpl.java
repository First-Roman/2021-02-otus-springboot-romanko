package ru.otus.questionnaire.service.view;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InteractiveImpl implements Interactive {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String string) {
        System.out.println(string);
    }

    @Override
    public String ask() {
        String answer = scanner.nextLine();
        return answer;
    }
}
