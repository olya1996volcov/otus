package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.UserResult;

public interface StringFormatterService {
    String getUserResult(UserResult userResult);
    String getQuestion(Question question);
}
