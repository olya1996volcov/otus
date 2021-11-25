package ru.otus.spring.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий для работы со студентами ")
@DataJpaTest
class BookRepositoryImplTest {


    @Autowired
    private BookRepository bookRepository;

    @DisplayName("должен находить книгу по id")
    @Test
    void findByIdTest() {
        long bookId = 111;
        Author author = new Author(22, "author_name_2", "author_surname_2");

        Book book = createBook(bookId, "book_name_1", new Genre(11, "genre_1"), author);
        val bookOpt = bookRepository.findById(bookId);
        Book actualBook = null;
        if(bookOpt.isPresent()) {
            actualBook = bookOpt.get();
        }
        assertNotNull(actualBook);
        assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("должен сохранять книгу")
    @Test
    void saveTest() {
        Book book = createBook(112, "book_title_new", new Genre(11, "genre_1"),
                new Author(22, "author_name_2", "author_surname_2"));
        bookRepository.save(book);
        val bookOpt = bookRepository.findById(book.getId());
        Book actualBook = null;
        if(bookOpt.isPresent()) {
            actualBook = bookOpt.get();
        }
        assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("должен находить все книги")
    @Test
    void findAllBooksTest() {
        val expectedBooks = 1;
        List<Book> books = bookRepository.findAll();
        assertEquals(expectedBooks, books.size());
        books.forEach(book -> {
                    assertNotNull(book.getTitle());
                    assertNotNull(book.getGenre());
                    assertNotNull(book.getAuthor());
                }
        );
    }


    private Book createBook(long bookId, String bookTitle, Genre genre, Author author) {
        return new Book(bookId, bookTitle, author, genre);
    }
}