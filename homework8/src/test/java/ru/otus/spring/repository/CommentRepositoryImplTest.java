package ru.otus.spring.repository;

import lombok.val;
import org.assertj.core.api.OffsetTimeAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе JPA для работы с комментариями должен ")
@DataJpaTest
class CommentRepositoryImplTest {

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("должен находить комментарий по id")
    @Test
    void findByIdTest() {
        val commentId = 1L;

        Comment expectedComment = Comment.builder()
                .text("Good book!")
                .book(Book.builder().id(111).build())
                .build();

        val actualCommentOptional = commentRepository.findById(commentId);
        assertThat(actualCommentOptional).isNotEmpty();

        val actualComment = actualCommentOptional.get();
        assertEquals(commentId, actualComment.getId());
        assertEquals(expectedComment.getText(), actualComment.getText());
        assertEquals(expectedComment.getBook().getId(), actualComment.getBook().getId());

    }

    @DisplayName("должен сохранять комментарий")
    @Test
    void saveTest() {
        Comment expectedComment = createComment("Some bad comment", Book.builder().id(111).build());
        commentRepository.save(expectedComment);

        val commentOptional = commentRepository.findById(expectedComment.getId());
        Comment actualComment = null;
        if(commentOptional.isPresent()) {
            actualComment = commentOptional.get();
        }
        assertNotNull(actualComment);
        assertThat(actualComment).isEqualTo(expectedComment);
    }

    @DisplayName("должен находить все комментарии по id книги")
    @Test
    void findAllTest() {
        val expectedCommentsSize = 1;
        val bookId = 111;
        List<Comment> comments = commentRepository.findAllByBookId(bookId);

        assertEquals(expectedCommentsSize, comments.size());
        comments.forEach(comment -> {
                    assertNotNull(comment.getText());
                    assertNotNull(comment.getBook());
                }
        );
    }

    @DisplayName("должен удалять комментарий по id")
    @Test
    void deleteTest() {
        val commentId = 1L;
        commentRepository.deleteById(commentId);
        val actualComment = commentRepository.findById(commentId);
        assertThat(actualComment).isEmpty();
    }

    private Comment createComment(String text, Book book) {
        return Comment.builder()
                .text(text)
                .book(book)
                .build();
    }
}