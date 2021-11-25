package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.service.IOService;

@Service
@RequiredArgsConstructor
public class AuthorCrudServiceImpl implements AuthorCrudService {

    private final IOService ioService;
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public void saveAuthor(String authorName, String authorSurname) {
        Author author = Author.builder()
                .authorName(authorName)
                .authorSurname(authorSurname)
                .build();
        authorRepository.save(author);

    }

    @Transactional(readOnly = true)
    @Override
    public void showAllAuthors() {
        authorRepository.findAll().forEach(author -> ioService.printString(author.toString()));
    }
}
