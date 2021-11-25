package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDto {
    private long id;

    private String authorName;

    private String authorSurname;

}
