package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReaderCsvFileTest {
    private static final String QUESTION1 = "Какой город является столицей Великобритании?";
    private static final String QUESTION2 = "Сколько ног у паука?";
    private static final String QUESTION3 = "Какова численость населения Санкт-Петербурга?";
    private static final String QUESTION4 = "Какая последняя версия Java на 2020 год?";
    private static final String QUESTION5 = "Какой город является столицей Соединенных Штатов Америки?";

    private static final int EXPECTED_QUESTION_SIZE = 5;

    @Autowired
    private ReaderCsvFile readerCsvFile;

    @Test
    void readQuestionsTest() {
        List<Question> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(new Question(QUESTION1));
        expectedQuestions.add(new Question(QUESTION2));
        expectedQuestions.add(new Question(QUESTION3));
        expectedQuestions.add(new Question(QUESTION4));
        expectedQuestions.add(new Question(QUESTION5));
        List<Question> actualQuestions = readerCsvFile.readQuestions();
        assertEquals(EXPECTED_QUESTION_SIZE, actualQuestions.size());
        assertEquals(expectedQuestions.toString(), actualQuestions.toString());

    }
}