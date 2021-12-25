package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.util.DtoDomainAutrhorMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorCrudServiceImpl implements AuthorCrudService {

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public AuthorDto saveAuthor(AuthorDto dto) {
        Author author = DtoDomainAutrhorMapper.toDomainObject(dto);
        return DtoDomainAutrhorMapper.toDto(authorRepository.save(author));
    }

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> showAllAuthors() {
        return authorRepository.findAll().stream().map(DtoDomainAutrhorMapper::toDto)
                .collect(Collectors.toList());
    }
}
