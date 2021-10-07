package ru.otus.spring.shell;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.BookRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Shell команды на основе Jdbc для работы с книгами  ")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Import(BookCrudCommands.class)
class BookCrudCommandsTest {

    @MockBean
    private BookRepositoryImpl bookRepository;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;
    @Autowired
    private BookCrudCommands bookCrudCommands;

    @DisplayName("должен сохранять книгу")
    @Test
    void saveBookTest() {
        val bookTitle = "book_title_new";
        val genre = new Genre(12, "genreName");
        val author = new Author(23, "Name", "Surname");
        bookCrudCommands.createBook(bookTitle, "genreName", "Name", "Surname");

        Mockito.verify(bookRepository).save(bookCaptor.capture());
        Book bookCaptureValue = bookCaptor.getValue();

        assertEquals(bookTitle, bookCaptureValue.getTitle());
        assertEquals(genre, bookCaptureValue.getGenre());
        assertEquals(author, bookCaptureValue.getAuthor());
    }

}