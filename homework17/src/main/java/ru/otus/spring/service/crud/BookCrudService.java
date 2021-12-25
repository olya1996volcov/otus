package ru.otus.spring.service.crud;

import ru.otus.spring.rest.dto.BookDto;

import java.util.List;

public interface BookCrudService {

    BookDto saveBook(BookDto book);

    BookDto findBookById(long id);

    List<BookDto> findAllBooks();

    BookDto updateBookTitleById(long bookId, String newTitle);

    void deleteBookById(long bookId);

}
