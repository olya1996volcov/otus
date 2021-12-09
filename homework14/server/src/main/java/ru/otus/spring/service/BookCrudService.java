package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookCrudService {

    BookDto saveBook(BookDto book);

    BookDto findBookById(long id);

    List<BookDto> findAllBooks();

    BookDto updateBookTitleById(long bookId, String newTitle);

    void deleteBookById(long bookId);

}
