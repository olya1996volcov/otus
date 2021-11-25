package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.rest.dto.CommentDto;
import ru.otus.spring.service.crud.BookCrudService;
import ru.otus.spring.service.crud.CommentCrudService;
import ru.otus.spring.util.DtoDomainMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CommentCrudService service;

    @Test
    void shouldReturnCorrectCommentListByBookId() throws Exception {
        Comment comment = createComment(1L);
        List<CommentDto> expectedResult = List.of(comment).stream()
                .map(DtoDomainMapper::toDto).collect(Collectors.toList());

        mvc.perform(get("/api/book/1/comment", 1).param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectCommentById() throws Exception {
        CommentDto expectedResult = DtoDomainMapper.toDto(createComment(1L));

        mvc.perform(get("/api/comment/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteCorrectCommentById() throws Exception {
        mvc.perform(delete("/api/comment/1"))
                .andExpect(status().isOk());
        Optional<Comment> optionalComment = service.showCommentById(1L);
        assertFalse(optionalComment.isPresent());

    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldCorrectSaveNewComment() throws Exception {

        String expectedResult = mapper.writeValueAsString(DtoDomainMapper.toDto(createComment(2L)));

        mvc.perform(post("/api/book/1/comment").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldCorrectUpdateComment() throws Exception {
        Comment comment = createComment(1L);
        comment.setText("newText");
        String expectedResult = mapper.writeValueAsString(DtoDomainMapper.toDto(comment));

        mvc.perform(put("/api/comment/1/text", 1).param("text", comment.getText())
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }


    private Comment createComment(long id) {
        Author author = Author.builder()
                .id(1)
                .authorName("author_name_1")
                .authorSurname("author_surname_1")
                .build();
        Genre genre = Genre.builder()
                .id(1)
                .genreName("genre_1")
                .build();
      Book book = Book.builder()
                .id(1L).title("book_name_1").genre(genre).author(author).build();
        return Comment.builder()
                .id(id)
                .book(book)
                .text("text1")
                .build();
    }
}
