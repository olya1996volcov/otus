package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;
import ru.otus.spring.domain.UserResult;

public interface QuestionUserService {
    User readUserData();
    UserResult quizStart(User user);
    int checkAnswer(Question question);
    void printResults(UserResult userResult);
}
