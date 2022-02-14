package ru.otus.spring.util;

import ru.otus.spring.domain.User;
import ru.otus.spring.rest.dto.UserDto;

public class DtoDomainUserMapper {
    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getSurname());
    }

    public static User toDomainObject(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getSurname());
    }

}
