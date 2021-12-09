package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;
import ru.otus.spring.rest.dto.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentCrudService {

    Comment saveComment(Comment comment, long bookId);

    Optional<Comment> showCommentById(long id);

    List<Comment> showAllCommentsByBookId(long bookId);

    Comment updateCommentTextById(long id, String text);

    void deleteCommentById(long id);

    void deleteAllCommentsByBookId(long bookId);

    void saveByBookId(CommentDto comment, long bookId);

}
