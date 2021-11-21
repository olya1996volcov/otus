package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookCrudService {

    Book saveBook(Book book);

    Optional<Book> findBookById(long id);

    List<Book> findAllBooks();

    Book updateBookTitleById(long bookId, String newTitle);

    void deleteBookById(long bookId);

}
