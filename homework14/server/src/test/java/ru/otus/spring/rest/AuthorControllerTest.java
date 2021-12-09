package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.util.DtoDomainMapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldReturnCorrectAuthorList() throws Exception {

        List<AuthorDto> expectedResult = createAuthorList().stream()
                .map(DtoDomainMapper::toDto).collect(Collectors.toList());

        mvc.perform(get("/api/author"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldCorrectSaveNewAuthor() throws Exception {

        String expectedResult = mapper.writeValueAsString(DtoDomainMapper.toDto(createAuthor()));

        mvc.perform(post("/api/author").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    private Author createAuthor() {
        return Author.builder()
                    .id(3L)
                    .authorName("author_name_3")
                    .authorSurname("author_surname_3")
                    .build();
    }

    private List<Author> createAuthorList() {
        Author author1 = Author.builder()
                .id(1L)
                .authorName("author_name_1")
                .authorSurname("author_surname_1")
                .build();
        Author author2 = Author.builder()
                .id(2L)
                .authorName("author_name_2")
                .authorSurname("author_surname_2")
                .build();
        return List.of(author1, author2);
    }


}
