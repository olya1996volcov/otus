package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
@Builder
public class Book {
    @Setter
    private long bookId;
    private final String title;
    private final Author author;
    private final Genre genre;

    @Override
    public String toString() {
        return bookId + ". '" + title + '\'' +
                ", author: " + author.getAuthorName() + ' ' + author.getAuthorSurname() +
                ", genre: " + genre.getGenreName();
    }
}
