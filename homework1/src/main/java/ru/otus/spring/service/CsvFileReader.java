package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvFileReader implements Reader {
    private final String fileName;

    CsvFileReader(String file) {
        fileName = file;
    }

    public List<Question> readQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                List<Answer> answers = new ArrayList<>();
                String[] splitLine = line.split(",");
                Question question = new Question();
                question.setQuestion(splitLine[0]);
                int count = 0;
                for (int i = 1; i < splitLine.length; i += 2) {
                    count++;
                    Answer answer = new Answer(count, splitLine[i], splitLine[i + 1].equals("1"));
                    answers.add(answer);
                }
                question.setAnswers(answers);
                questions.add(question);
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error in Reader");
        }
        return questions;
    }
}
