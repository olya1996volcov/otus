package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.rest.dto.CommentDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentCrudServiceImpl implements CommentCrudService {

    private final CommentRepository commentRepository;


    @Transactional
    @Override
    public Comment saveComment(Comment comment, long bookId) {
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> showCommentById(long id) {
        return commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> showAllCommentsByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Transactional
    @Override
    public Comment updateCommentTextById(long id, String text) {

        Optional<Comment> commentOptional = showCommentById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(text);
            return commentRepository.save(comment);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllCommentsByBookId(long bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }

    @Transactional
    @Override
    public void saveByBookId(CommentDto comment, long bookId) {
          commentRepository.saveByBookId(comment.getId(), comment.getText(), bookId);

    }
}
