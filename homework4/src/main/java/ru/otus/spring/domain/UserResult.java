package ru.otus.spring.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserResult {
    private final User user;
    private final int result;

    public User getUser() {
        return user;
    }

    public int getResult() {
        return result;
    }
}
