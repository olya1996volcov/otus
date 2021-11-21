package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

@Data
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String text;
    private long bookId;

    public static CommentDto toDto(long id, String text, Long bookId) {
        return new CommentDto(id, text, bookId);
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText(),  comment.getBook().getId());
    }


    public static Comment toDomainObject(CommentDto dto) {
        return new Comment(dto.getId(), dto.getText(), Book.builder().id(dto.getBookId()).build());
    }

}
