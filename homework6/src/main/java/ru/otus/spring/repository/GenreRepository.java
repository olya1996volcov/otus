package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository {

    Genre save(Genre genre);

    List<Genre> findAll();

    Genre findByName(String name);
}
