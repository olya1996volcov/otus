package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorRepository {

    Author save(Author author);

    List<Author> findAll();

    Author findByName(String name, String surname);
}
