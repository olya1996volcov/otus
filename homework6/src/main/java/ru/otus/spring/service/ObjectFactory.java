package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.GenreRepository;

@Service
@RequiredArgsConstructor
public class ObjectFactory {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public Book createBook(String title, String authorName, String authorSurname, String genreName) {
        Author author = getOrCreateAuthor(authorName, authorSurname);
        Genre genre = getOrCreateGenre(genreName);
        Book book = Book.builder()
                .title(title)
                .author(author)
                .genre(genre)
                .build();

        return book;
    }

    private Genre getOrCreateGenre(String genreName) {
        Genre genre = genreRepository.findByName(genreName);
        if (genre != null) {
            return genre;
        }
        genre = genreRepository.save(Genre.builder().genreName(genreName).build());
        return genre;
    }

    private Author getOrCreateAuthor(String authorName, String authorSurname) {
        Author author = authorRepository.findByName(authorName, authorSurname);
        if (author != null) {
            return author;
        }
        author = authorRepository.save(Author.builder()
                .authorName(authorName)
                .authorSurname(authorSurname)
                .build());
        return author;
    }
}
