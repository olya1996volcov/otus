package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.UserResult;

@Service
@RequiredArgsConstructor
public class StringFormatterServiceImpl implements StringFormatterService {
    private final LocalizationService localizationService;

    @Override
    public String getUserResult(UserResult userResult) {
        return localizationService.getLocalMessage("output.result", userResult.getUser().getName(),
                userResult.getUser().getSurname(), userResult.getResult());
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
            i++;
        }
        return str.toString();
    }
}
