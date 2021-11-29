package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private long id;

    private String role;

    private String login;

    private String password;

    private Boolean enabled;
}
