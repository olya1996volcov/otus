package ru.otus.spring.util;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.rest.dto.CommentDto;
import ru.otus.spring.rest.dto.GenreDto;

public class DtoDomainAutrhorMapper {

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getAuthorName(),
                author.getAuthorSurname());
    }

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.getId(), dto.getAuthorName(), dto.getAuthorSurname());
    }

}
