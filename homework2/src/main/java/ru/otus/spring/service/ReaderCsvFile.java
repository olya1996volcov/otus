package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:application.yml")
public class ReaderCsvFile implements Reader {
   // private final String fileName = "homework2/src/main/resources/file.csv";
    private final IOService ioService;
    private final String fileName;


    public ReaderCsvFile(IOService ioService, @Value("${file-name}") String fileName) {
        this.ioService = ioService;
        this.fileName = fileName;
    }

    public List<Question> readQuestions() {
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
            ioService.print("Error in ReaderCsvFile");
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
