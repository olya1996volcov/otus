package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.StringFormatter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorCrudServiceImpl implements AuthorCrudService {

    private final IOService ioService;
    private final AuthorRepository authorRepository;
    private final StringFormatter stringFormatter;

    @Transactional
    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> showAllAuthors() {
        return authorRepository.findAll();
    }
}
