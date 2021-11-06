package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.crud.BookCrudService;

@ShellComponent
@RequiredArgsConstructor
public class BookCrudCommands {
    private final BookCrudService bookService;
    private final IOService ioService;
    private final ObjectFactory objectFactory;

    @ShellMethod(value = "Create book (example: cb book_name_3 genre_name_2 Name1 Surname1)",
            key = {"cb", "create book"})
    public void createBook(@ShellOption String title, @ShellOption String genre,
                           @ShellOption String authorName, @ShellOption String authorSurname) {
        bookService.saveBook(title, genre, authorName, authorSurname);

    }

    @ShellMethod(value = "Read all books (example: rab)", key = {"rab", "read all books"})
    public void readBooks() {
        bookService.findAllBooks();
    }

    @ShellMethod(value = "Delete book by id (example: db 1)", key = {"db", "delete book"})
    public void deleteBook(@ShellOption long bookId) {
        bookService.deleteBookById(bookId);
    }

    @ShellMethod(value = "Update title book by id (example: ubt 1 new_title)", key = {"ubt", "update book title"})
    public void updateTitleBook(@ShellOption long bookId, @ShellOption String newTitle) {
        bookService.updateBookTitleById(bookId, newTitle);
    }
}
