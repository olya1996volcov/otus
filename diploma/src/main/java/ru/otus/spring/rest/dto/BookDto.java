package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

@Data
@AllArgsConstructor
public class BookDto {

    private long id;
    private String title;
    private Author author;
    private Genre genre;
    private boolean isFree;


}
