package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void updateTitle(long bookId, String newTitle);

    void delete(long bookId);
}
