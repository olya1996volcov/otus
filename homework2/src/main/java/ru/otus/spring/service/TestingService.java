package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface TestingService {
    void testUser();
    int checkAnswer(Question question);
}
