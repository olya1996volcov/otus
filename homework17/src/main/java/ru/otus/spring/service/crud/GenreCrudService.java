package ru.otus.spring.service.crud;

import ru.otus.spring.rest.dto.GenreDto;

import java.util.List;

public interface GenreCrudService {

    GenreDto saveGenre(GenreDto genre);

    List<GenreDto> showAllGenres();
}
