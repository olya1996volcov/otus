package ru.otus.spring.service.crud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.BookDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookCrudServiceImplTest {
    @Autowired
    private BookCrudService service;

    @Test
    void saveBookTest() {
        BookDto expectedBookDto = createBookDto(3L, "book_name_3");
        BookDto bookDtoActual = service.saveBook(expectedBookDto);

        assertEquals(expectedBookDto, bookDtoActual);
    }


    @Test
    @DirtiesContext
    void findBookByIdTest() {
        BookDto expectedBookDto = createBookDto(1L, "book_name_1");
        BookDto actualBookById = service.findBookById(1L);

        assertEquals(expectedBookDto, actualBookById);

    }

    @Test
    @DirtiesContext
    void findAllBooksTest() {
        List<BookDto> allBooks = service.findAllBooks();
        assertEquals(2, allBooks.size());
    }

    @Test
    void updateBookTitleByIdTest() {
        final String title = "new Title";
        BookDto bookDto = service.updateBookTitleById(1L, title);
        BookDto expectedBookDto = createBookDto(1L, title);

        assertEquals(expectedBookDto, bookDto);
    }

    @Test
    void deleteBookByIdTest() {
        int initialSize = service.findAllBooks().size();
        service.deleteBookById(2L);
        int finalSize = service.findAllBooks().size();
        assertEquals(1, initialSize - finalSize);

    }


    private BookDto createBookDto(long id, String title) {
        Author author1 = Author.builder()
                .id(1)
                .authorName("author_name_1")
                .authorSurname("author_surname_1")
                .build();
        Genre genre = Genre.builder()
                .id(1)
                .genreName("genre_1")
                .build();
        return BookDto.builder()
                .id(id).title(title).genre(genre).author(author1).isFree(true).build();
    }

}