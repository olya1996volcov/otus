package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.crud.AuthorCrudService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCrudCommands {
    private final IOService ioService;
    private final AuthorCrudService authorService;

    @ShellMethod(value = "Create author (example: ca name surname)",
            key = {"ca", "create author"})
    public void createAuthor(@ShellOption String authorName, @ShellOption String authorSurname) {
        authorService.saveAuthor(authorName, authorSurname);

    }

    @ShellMethod(value = "Read all authors (example: raa)", key = {"raa", "read all authors"})
    public void readAuthors() {
        authorService.showAllAuthors();

    }

}
