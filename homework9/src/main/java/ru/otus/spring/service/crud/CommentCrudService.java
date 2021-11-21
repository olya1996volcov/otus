package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentCrudService {

    Comment saveComment(Comment comment, long bookId);

    Optional<Comment> showCommentById(long id);

    List<Comment> showAllCommentsByBookId(long bookId);

    Comment updateCommentTextById(long id, String text);

    void deleteCommentById(long id);

    void deleteAllCommentsByBookId(long bookId);

    void saveByBookId(Comment comment, long bookId);

}
