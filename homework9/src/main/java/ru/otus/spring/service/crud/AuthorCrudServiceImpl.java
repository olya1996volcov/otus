package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorCrudServiceImpl implements AuthorCrudService {

    private final AuthorRepository authorRepository;

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
