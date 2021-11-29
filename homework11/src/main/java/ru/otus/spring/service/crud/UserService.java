package ru.otus.spring.service.crud;

import ru.otus.spring.rest.dto.GenreDto;
import ru.otus.spring.rest.dto.UserDto;

public interface UserService {
    UserDto saveUser(UserDto user);
}
