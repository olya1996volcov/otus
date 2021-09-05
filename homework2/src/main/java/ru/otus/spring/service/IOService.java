package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestUserResult;

public interface IOService {
    void print(String str);

    String readString();

    void printQuestion(Question q);
}
