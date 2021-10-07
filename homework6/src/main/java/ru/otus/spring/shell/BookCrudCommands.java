package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;

@ShellComponent
@RequiredArgsConstructor
public class BookCrudCommands {
    private final BookRepository bookRepository;
    private final IOService ioService;
    private final ObjectFactory objectFactory;

    @ShellMethod(value = "Create book (example: cb book_name_3 genre_name_2 Name1 Surname1)",
            key = {"cb", "create book"})
    public void createBook(@ShellOption String title, @ShellOption String genre,
                           @ShellOption String authorName, @ShellOption String authorSurname) {
        Book book = objectFactory.createBook(title, authorName, authorSurname, genre);
        bookRepository.save(book);
    }

    @ShellMethod(value = "Read all books (example: rab)", key = {"rab", "read all books"})
    public void readBooks() {
        bookRepository.findAll().forEach(book -> ioService.printString(book.toString()));
    }

    @ShellMethod(value = "Delete book by id (example: db 1)", key = {"db", "delete book"})
    public void deleteBook(@ShellOption long bookId) {
        bookRepository.delete(bookId);
    }

    @ShellMethod(value = "Update title book by id (example: ubt 1 new_title)", key = {"ubt", "update book title"})
    public void updateTitleBook(@ShellOption long bookId, @ShellOption String newTitle) {
        bookRepository.updateTitle(bookId, newTitle);
    }
}
