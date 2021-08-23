package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private final CsvFileReader reader;

    IOServiceImpl(CsvFileReader reader) {
        this.reader = reader;
    }

    @Override
    public void printQuestions() {
        List<Question> questionList = reader.readQuestions();
        for (Question q : questionList) {
            System.out.println(q.toString());
        }
    }
}
