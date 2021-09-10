package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.TestUserResult;
import ru.otus.spring.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Class StringFormatterService")
public class StringFormatterServiceTest {
    @DisplayName("correct getTestingResult")
    @Test
    void getTestingResultTest() {
        User user = new User("Ivan", "Ivanov");
        TestUserResult userResult = new TestUserResult(user, 0);
        StringFormatterService stringFormatterService = new StringFormatterServiceImpl();
        assertEquals("User Ivan Ivanov gives 0 correct answers out of 5",
                stringFormatterService.getTestingResult(userResult));

    }

}
