package ru.otus.spring.domain;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Question {
    private String question;
    private List<Answer> answers;

    public Question() {
        question = null;
        answers = new ArrayList<>();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Answer answer : answers) {
            sb.append(answer.toString());
        }
        return question + "\n" + sb.toString();
    }
}
