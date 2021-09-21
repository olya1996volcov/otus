package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface QuestionUserService {
    void quizStart();
    int checkAnswer(Question question);
}
