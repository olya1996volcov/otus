package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.User;
import ru.otus.spring.domain.UserResult;
import ru.otus.spring.service.QuestionUserService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private final QuestionUserService questionUserService;
    private UserResult userResult;
    private User user;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public void login(@ShellOption(defaultValue = "AnyName") String name,
                      @ShellOption(defaultValue = "AnySurname") String surName) {
        user = questionUserService.readUserData();
    }

    @ShellMethod(value = "Test", key = {"t", "test"})
    @ShellMethodAvailability(value = "isUserLoginSuccess")
    public void test() {
        userResult = questionUserService.quizStart(user);
    }

    @ShellMethod(value = "Print result", key = {"pr", "print result"})
    @ShellMethodAvailability(value = "isUserTesting")
    public void showResult() {
        questionUserService.printResults(userResult);
    }

    private Availability isUserLoginSuccess() {
        return user == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }

    private Availability isUserTesting() {
        return userResult == null ? Availability.unavailable("Сначала пройдите тестирование") : Availability.available();
    }
}
