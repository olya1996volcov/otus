package ru.otus.spring.util;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.rest.dto.CommentDto;
import ru.otus.spring.rest.dto.GenreDto;

public class DtoDomainMapper {
    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getAuthorName(),
                author.getAuthorSurname());
    }

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.getId(), dto.getAuthorName(), dto.getAuthorSurname());
    }

    public static Book toDomainObject(BookDto dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getGenre());
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(),
                book.getGenre());
    }
    public static CommentDto toDto(long id, String text, Long bookId) {
        return new CommentDto(id, text, bookId);
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText(),  comment.getBook().getId());
    }


    public static Comment toDomainObject(CommentDto dto) {
        return new Comment(dto.getId(), dto.getText(), Book.builder().id(dto.getBookId()).build());
    }

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getGenreName());
    }

    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.getId(), dto.getGenreName());
    }

}
