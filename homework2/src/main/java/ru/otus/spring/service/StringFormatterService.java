package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestUserResult;

public interface StringFormatterService {
    String getTestingResult( TestUserResult testUserResult);
    String getQuestion(Question question);
}
