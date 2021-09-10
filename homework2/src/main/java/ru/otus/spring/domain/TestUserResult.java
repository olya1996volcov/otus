package ru.otus.spring.domain;

public class TestUserResult {
    private final User user;
    private final int testResult;

    public TestUserResult(User user, int testResult) {
        this.user = user;
        this.testResult = testResult;
    }

    public User getUser() {
        return user;
    }

    public int getTestResult() {
        return testResult;
    }
}
