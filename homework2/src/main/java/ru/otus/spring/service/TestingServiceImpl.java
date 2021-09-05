package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestUserResult;
import ru.otus.spring.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final IOService ioService;
    private final StringFormatterService stringFormatter;
    private final Reader reader;

    @Override
    public void testUser() {

        ioService.print("Please, enter your name:");
        String userName = ioService.readString();
        ioService.print("Please, enter your surname:");
        String userSurname = ioService.readString();
        User user = new User(userName, userSurname);
        int result = 0;

        List<Question> questionList = reader.readQuestions();
        for (Question q : questionList) {
            ioService.printQuestion(q);
            result += checkAnswer(q);
        }
        TestUserResult testResult = new TestUserResult(user, result);
        ioService.print(stringFormatter.getTestingResult(testResult));
    }

    @Override
    public int checkAnswer(Question question) {
        int userEnteredAnswer;
        while (true) {
            try {
                userEnteredAnswer = Integer.parseInt(ioService.readString());
                if (question.getRightById(userEnteredAnswer)) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                ioService.print("Please, write number of answer (1-" + question.getAnswers().size() + ")");
            }
        }
    }
}
