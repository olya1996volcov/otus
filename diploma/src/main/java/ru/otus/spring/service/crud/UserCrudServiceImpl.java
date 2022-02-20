package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.User;
import ru.otus.spring.repository.UserRepository;
import ru.otus.spring.rest.NotFoundException;
import ru.otus.spring.rest.dto.UserDto;
import ru.otus.spring.util.DtoDomainUserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCrudServiceImpl implements UserCrudService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDto saveUser(UserDto dto) {
        User user = DtoDomainUserMapper.toDomainObject(dto);
        return DtoDomainUserMapper.toDto(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findUserById(long id) {
        return DtoDomainUserMapper.toDto(userRepository.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(DtoDomainUserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteUserById(long clientId) {
        userRepository.deleteById(clientId);
    }
}
