package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookCrudService bookService;

    @PostMapping(value = "/api/book")
    public BookDto createNewBook(@RequestBody BookDto dto) {
        return bookService.saveBook(dto);
    }

    @GetMapping(value = "/api/show")
    public List<BookDto> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/api/book/{id}")
    public BookDto getBookById(@PathVariable("id") long id) {
        return bookService.findBookById(id);

    }

    @DeleteMapping("/api/book/{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
    }

    @PutMapping("/api/book/{id}/title")
    public BookDto updateBookTitleById(@PathVariable("id") long id, @RequestParam("title") String title) {
        return bookService.updateBookTitleById(id, title);
    }
}
