package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.rest.dto.UserDto;
import ru.otus.spring.service.crud.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/api/user")
    public UserDto createNewUser(@RequestBody UserDto dto) {
        return userService.saveUser(dto);
    }
}
