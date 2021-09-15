package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface IOService {
    void write(String str);

    String readString();

    void writeQuestion(Question q);
}
