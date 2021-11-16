package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjectFactory {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

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

    public Comment createComment(String commentText, long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {

            Book book = optionalBook.get();
            Comment comment = Comment.builder()
                    .book(book)
                    .text(commentText)
                    .build();
            return comment;
        }

        return null;
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
        Optional<Author> author = authorRepository.findByFullName(authorName, authorSurname);
        return author.orElseGet(() -> authorRepository.save(Author.builder()
                .authorName(authorName)
                .authorSurname(authorSurname)
                .build()));
    }
}
