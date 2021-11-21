package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.rest.dto.GenreDto;
import ru.otus.spring.service.crud.AuthorCrudService;
import ru.otus.spring.service.crud.GenreCrudService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class GenreController {
    private final GenreCrudService genreService;

    @PostMapping(value = "/api/genre")
    public GenreDto createNewGenre(@RequestBody GenreDto dto) {
        Genre genre = GenreDto.toDomainObject(dto);
        Genre savedGenre = genreService.saveGenre(genre);
        return GenreDto.toDto(savedGenre);
    }

    @GetMapping(value = "/api/genre")
    public List<GenreDto> getAllAuthors() {
        return genreService.showAllGenres().stream().map(GenreDto::toDto)
                .collect(Collectors.toList());
    }
}
