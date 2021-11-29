package ru.otus.spring.util;

import ru.otus.spring.domain.*;
import ru.otus.spring.rest.dto.*;

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
    public static UserDto toDto(AppUser user) {
        return new UserDto(user.getId(), user.getRole(), user.getLogin(), user.getPasswordHash(), user.getEnabled());
    }
    public static AppUser toDomainObject(UserDto user) {
        return new AppUser(user.getId(), user.getRole(), user.getLogin(), user.getPassword(), user.getEnabled());
    }
}
