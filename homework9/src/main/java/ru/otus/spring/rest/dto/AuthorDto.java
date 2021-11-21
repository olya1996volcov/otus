package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Author;

@Data
@AllArgsConstructor
public class AuthorDto {
    private long id;

    private String authorName;

    private String authorSurname;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getAuthorName(),
                author.getAuthorSurname());
    }

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.getId(), dto.getAuthorName(), dto.getAuthorSurname());
    }
}
