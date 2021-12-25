package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.rest.dto.GenreDto;
import ru.otus.spring.service.GenreCrudService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GenreController {
    private final GenreCrudService genreService;

    @PostMapping(value = "/api/genre")
    public GenreDto createNewGenre(@RequestBody GenreDto dto) {
        return genreService.saveGenre(dto);
    }

    @GetMapping(value = "/api/genre")
    public List<GenreDto> getAllAuthors() {
        return genreService.showAllGenres();
    }
}
