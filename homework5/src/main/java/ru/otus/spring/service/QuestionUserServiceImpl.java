package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;
import ru.otus.spring.domain.UserResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionUserServiceImpl implements QuestionUserService {

    private final IOService ioService;
    private final StringFormatterService stringFormatter;
    private final Reader reader;
    private final LocalizationService localizationService;

    @Override
    public User readUserData() {
        ioService.write(localizationService.getLocalMessage("input.name"));
        String userName = ioService.readString();
        ioService.write(localizationService.getLocalMessage("input.surname"));
        String userSurname = ioService.readString();
        return new User(userName, userSurname);
    }

    @Override
    public UserResult quizStart(User user) {
        int result = 0;

        List<Question> questionList = reader.readQuestions();
        for (Question q : questionList) {
            ioService.writeQuestion(q);
            result += checkAnswer(q);
        }
        return new UserResult(user, result);
    }

    @Override
    public int checkAnswer(Question question) {
        int userEnteredAnswer;
        while (true) {
            try {
                userEnteredAnswer = Integer.parseInt(ioService.readString());
                return question.getIsRightById(userEnteredAnswer) ? 1 : 0;
            } catch (Exception e) {
                ioService.write(localizationService.getLocalMessage
                        ("input.question.wrong", question.getAnswers().size()));
            }
        }
    }

    @Override
    public void printResults(UserResult userResult) {
        ioService.write(stringFormatter.getUserResult(userResult));
    }
}
