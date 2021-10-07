package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.service.IOService;

@ShellComponent
@RequiredArgsConstructor
public class GenreCrudCommands {
    private final IOService ioService;
    private final GenreRepository genreRepository;

    @ShellMethod(value = "Create genre (example: cg name)",
            key = {"cg", "create genre"})
    public void createGenre(@ShellOption String name) {
        Genre genre = Genre.builder()
                .genreName(name)
                .build();
        genreRepository.save(genre);
    }

    @ShellMethod(value = "Read all genres (example: rag)", key = {"rag", "read all genres"})
    public void readGenres() {
        genreRepository.findAll().forEach(genre -> ioService.printString(genre.toString()));
    }
}
