package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenreDto {
    private long id;
    private String genreName;

}
