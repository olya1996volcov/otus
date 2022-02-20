package ru.otus.spring.service.crud;

import ru.otus.spring.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorCrudService {

    AuthorDto saveAuthor(AuthorDto author);

    List<AuthorDto> showAllAuthors();
}
