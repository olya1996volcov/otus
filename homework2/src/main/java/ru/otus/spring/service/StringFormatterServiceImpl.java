package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestUserResult;

@Service
public class StringFormatterServiceImpl implements StringFormatterService {
    @Override
    public String getTestingResult(TestUserResult testUserResult) {

        return "User " + testUserResult.getUser().getName() + " " + testUserResult.getUser().getSurname() +
                " gives " + testUserResult.getTestResult() + " correct answers out of 5";
    }

    @Override
    public String getQuestion(Question question) {
        StringBuilder str = new StringBuilder();
        str.append(question.getQuestion())
                .append("\n");
        int i = 1;
        for (Answer a : question.getAnswers()) {
            str.append(i)
                    .append(")")
                    .append(a.getAnswer())
                    .append("\t\t");
        }
        return str.toString();
    }
}
