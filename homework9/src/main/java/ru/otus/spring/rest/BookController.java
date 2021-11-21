package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookCrudService bookService;

    @PostMapping(value = "/api/book")
    public BookDto createNewBook(@RequestBody BookDto dto) {
        Book book = BookDto.toDomainObject(dto);
        Book savedBook = bookService.saveBook(book);
        return BookDto.toDto(savedBook);
    }

    @GetMapping(value = "/api/book")
    public List<BookDto> getAllBooks() {
        return bookService.findAllBooks().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/book/{id}")
    public BookDto getBookById(@PathVariable("id") long id) {
        Book book = bookService.findBookById(id).orElseThrow(NotFoundException::new);

        return BookDto.toDto(book);
    }

    @DeleteMapping("/api/book/{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
    }

    @PutMapping("/api/book/{id}/title")
    public BookDto updateBookTitleById(@PathVariable("id") long id, @RequestParam("title") String title) {
        return BookDto.toDto(bookService.updateBookTitleById(id, title));
    }
}
