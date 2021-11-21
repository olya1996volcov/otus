package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Author;
import ru.otus.spring.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorCrudService {

    Author saveAuthor(Author author);

    List<Author> showAllAuthors();
}
