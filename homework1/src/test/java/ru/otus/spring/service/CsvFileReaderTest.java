package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileReaderTest {
    private final CsvFileReader reader = new CsvFileReader("src\\test\\resources\\file1.csv");

    @org.junit.jupiter.api.Test
    void readQuestionsTest() {
        List<Question> questions = reader.readQuestions();
        assertNotNull(questions);
        assertEquals(5, questions.size());
    }
}