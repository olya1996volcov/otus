package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.service.crud.AuthorCrudService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorCrudService authorService;

    @PostMapping(value = "/api/author")
    public AuthorDto createNewAuthor(@RequestBody AuthorDto dto) {
        return authorService.saveAuthor(dto);
    }

    @GetMapping(value = "/api/author")
    public List<AuthorDto> getAllAuthors() {
        return authorService.showAllAuthors();
    }

}
