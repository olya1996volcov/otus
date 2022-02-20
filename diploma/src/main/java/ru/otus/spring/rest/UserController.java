package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.UserDto;
import ru.otus.spring.service.crud.UserCrudService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserCrudService userService;


    @PostMapping(value = "/api/user")
    public UserDto createNewUser(@RequestBody UserDto dto) {
        return userService.saveUser(dto);
    }

    @GetMapping(value = "/api/user")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/api/user/{id}")
    public UserDto getUserById(@PathVariable("id") long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/api/user/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        userService.deleteUserById(id);
    }

}
