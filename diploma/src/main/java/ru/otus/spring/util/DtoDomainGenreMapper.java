package ru.otus.spring.util;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.GenreDto;

public class DtoDomainGenreMapper {

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getGenreName());
    }

    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.getId(), dto.getGenreName());
    }

}
