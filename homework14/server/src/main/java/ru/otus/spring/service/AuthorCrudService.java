package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorCrudService {

    AuthorDto saveAuthor(AuthorDto author);

    List<AuthorDto> showAllAuthors();
}
