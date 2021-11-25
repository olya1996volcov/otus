package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

@Service
@RequiredArgsConstructor
public class StringFormatter {
    public String getString(Book book) {
        return book.getId() + ". '" + book.getTitle() + '\'' +
                ", author: " + book.getAuthor().getAuthorName() + ' ' + book.getAuthor().getAuthorSurname() +
                ", genre: " + book.getGenre().getGenreName();
    }

    public String getString(Genre genre) {
            return genre.getId() + ". " + genre.getGenreName();
    }

    public String getString(Author author) {
        return author.getId() + ". " + author.getAuthorName() + ' ' + author.getAuthorSurname();
    }

    public String getString(Comment comment) {
        return comment.getId() +". " + comment.getBook().getTitle() + ": " + comment.getText();
    }
}
