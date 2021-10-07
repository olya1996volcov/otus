package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderCsvFile implements Reader {

    private final IOService ioService;
    private final AppProps props;

    public List<Question> readQuestions() {
        String fileName = props.getFileName();
        List<Question> questions = new ArrayList<>();
        try {
            File file = getFileFromResources(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                List<Answer> answers = new ArrayList<>();
                String[] splitLine = line.split(",");
                Question question = new Question(splitLine[0]);
                for (int i = 1; i < splitLine.length; i += 2) {
                    Answer answer = new Answer(splitLine[i], splitLine[i + 1].equals("1"));
                    answers.add(answer);
                }
                question.setAnswers(answers);
                questions.add(question);
                line = reader.readLine();
            }

        } catch (IOException e) {
            ioService.write("Error in ReaderCsvFile");
        }
        return questions;
    }

    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
