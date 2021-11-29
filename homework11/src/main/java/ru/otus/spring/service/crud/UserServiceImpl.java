package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.AppUser;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.UserRepository;
import ru.otus.spring.rest.dto.UserDto;
import ru.otus.spring.util.DtoDomainMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDto saveUser(UserDto user) {
        AppUser genre = DtoDomainMapper.toDomainObject(user);
        return DtoDomainMapper.toDto(userRepository.save(genre));
    }
}
