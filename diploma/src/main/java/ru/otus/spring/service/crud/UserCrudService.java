package ru.otus.spring.service.crud;

import ru.otus.spring.rest.dto.UserDto;

import java.util.List;

public interface UserCrudService {

    UserDto saveUser(UserDto book);

    UserDto findUserById(long id);

    List<UserDto> findAllUsers();

    void deleteUserById(long userId);
}
