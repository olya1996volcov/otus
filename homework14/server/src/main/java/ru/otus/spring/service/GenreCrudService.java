package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.GenreDto;

import java.util.List;

public interface GenreCrudService {

    GenreDto saveGenre(GenreDto genre);

    List<GenreDto> showAllGenres();
}
