package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.service.IOService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCrudCommands {
    private final IOService ioService;
    private final AuthorRepository authorRepository;

    @ShellMethod(value = "Create author (example: ca name surname)",
            key = {"ca", "create author"})
    public void createAuthor(@ShellOption String authorName, @ShellOption String authorSurname) {
        Author author = Author.builder()
                .authorName(authorName)
                .authorSurname(authorSurname)
                .build();
        authorRepository.save(author);
    }

    @ShellMethod(value = "Read all authors (example: raa)", key = {"raa", "read all authors"})
    public void readAuthors() {
        authorRepository.findAll().forEach(author -> ioService.printString(author.toString()));
    }

}
