package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.crud.GenreCrudService;

@ShellComponent
@RequiredArgsConstructor
public class GenreCrudCommands {
    private final IOService ioService;
    private final GenreCrudService genreService;

    @ShellMethod(value = "Create genre (example: cg name)",
            key = {"cg", "create genre"})
    public void createGenre(@ShellOption String name) {
        genreService.saveGenre(name);

    }

    @ShellMethod(value = "Read all genres (example: rag)", key = {"rag", "read all genres"})
    public void readGenres() {
        genreService.showAllGenres();
    }
}
