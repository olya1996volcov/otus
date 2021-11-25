package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;

@Service
@RequiredArgsConstructor
public class CommentCrudServiceImpl implements CommentCrudService {

    private final CommentRepository commentRepository;
    private final IOService ioService;
    private final ObjectFactory objectFactory;

    @Transactional
    @Override
    public void saveComment(String title, long bookId) {
        Comment comment = objectFactory.createComment(title, bookId);
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public void showCommentById(long id) {
        commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllCommentsByBookId(long bookId) {
        commentRepository.findAllByBookId(bookId)
                .forEach(comment -> ioService.printString(comment.toString()));
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllComments() {
        commentRepository.findAll()
                .forEach(comment -> ioService.printString(comment.toString()));
    }

    @Transactional
    @Override
    public void updateCommentTextById(long id, String text) {
        commentRepository.updateTextById(id, text);
    }

    @Transactional
    @Override
    public void deleteCommentById(long id) {
        commentRepository.delete(id);
    }

    @Transactional
    @Override
    public void deleteAllCommentsByBookId(long bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }
}
