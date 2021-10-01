package ru.otus.spring.domain;

import lombok.Data;

@Data
public class UserResult {
    private final User user;
    private final int result;
}
