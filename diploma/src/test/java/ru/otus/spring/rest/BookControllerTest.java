package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;
import ru.otus.spring.util.DtoDomainBookMapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BookCrudService service;
//
//    @WithMockUser(
//            username = "admin",
//            password = "admin",
//            roles = "ADMIN"
//    )

    @Test
    void shouldReturnCorrectBookList() throws Exception {

        List<BookDto> expectedResult = createBookList().stream()
                .map(DtoDomainBookMapper::toDto).collect(Collectors.toList());

        mvc.perform(get("/api/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

//    @WithMockUser(
//            username = "admin",
//            password = "admin",
//            roles = "ADMIN"
//    )

    @Test
    void shouldReturnCorrectBookById() throws Exception {
        BookDto expectedResult = DtoDomainBookMapper.toDto(createOneBook(1L));

        mvc.perform(get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)))
        ;
    }

//    @WithMockUser(
//            username = "admin",
//            password = "admin",
//            roles = "ADMIN"
//    )

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteCorrectBookById() throws Exception {
        mvc.perform(delete("/api/book/2"))
                .andExpect(status().isOk());
        assertThrows(NotFoundException.class, () -> {
            service.findBookById(2L);
        });

    }

//    @WithMockUser(
//            username = "admin",
//            password = "admin",
//            roles = "ADMIN"
//    )

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldCorrectSaveNewBook() throws Exception {

        String expectedResult = mapper.writeValueAsString(DtoDomainBookMapper.toDto(createOneBook(3L)));

        mvc.perform(post("/api/book").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

//    @WithMockUser(
//            username = "admin",
//            password = "admin",
//            roles = "ADMIN"
//    )

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldCorrectUpdateBookTitle() throws Exception {
        Book book = createOneBook(1L);
        book.setTitle("newTitle");
        String expectedResult = mapper.writeValueAsString(DtoDomainBookMapper.toDto(book));

        mvc.perform(put("/api/book/{id}/title", 1).param("title", book.getTitle())
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }


    private Book createOneBook(Long id) {
        Author author1 = Author.builder()
                .id(1)
                .authorName("author_name_1")
                .authorSurname("author_surname_1")
                .build();
        Genre genre = Genre.builder()
                .id(1)
                .genreName("genre_1")
                .build();
        return Book.builder()
                .id(id).title("book_name_1").genre(genre).author(author1).isFree(true).build();

    }

    List<Book> createBookList() {
        Author author1 = Author.builder()
                .id(1)
                .authorName("author_name_1")
                .authorSurname("author_surname_1")
                .build();
        Author author2 = Author.builder()
                .id(2)
                .authorName("author_name_2")
                .authorSurname("author_surname_2")
                .build();
        Genre genre = Genre.builder()
                .id(1)
                .genreName("genre_1")
                .build();

        return List.of(Book.builder()
                        .id(1).title("book_name_1").genre(genre).author(author1).isFree(true)
                        .build(),
                Book.builder()
                        .id(2).title("book_name_2").genre(genre).author(author2).isFree(true)
                        .build());

    }

}
