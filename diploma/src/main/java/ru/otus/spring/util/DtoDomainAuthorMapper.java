package ru.otus.spring.util;

import ru.otus.spring.domain.Author;
import ru.otus.spring.rest.dto.AuthorDto;

public class DtoDomainAuthorMapper {

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getAuthorName(),
                author.getAuthorSurname());
    }

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.getId(), dto.getAuthorName(), dto.getAuthorSurname());
    }

}
