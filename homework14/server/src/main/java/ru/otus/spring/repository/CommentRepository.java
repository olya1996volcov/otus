package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("update Comment c set c.text = :text where c.id = :id")
    void updateTextById(@Param("id") long id, @Param("text") String text);

    @Modifying
    @Query(value = "insert into comments (id, text, book_id) values (:id, :text, :bookid)", nativeQuery = true)
    void saveByBookId(@Param("id") long id, @Param("text") String text, @Param("bookid") long bookId);

    @Modifying
    @Query("delete from Comment c where c.book.id = :id")
    void deleteAllByBookId(@Param("id") long bookId);

    @Query("select c from Comment c where c.book.id = :id")
    List<Comment> findAllByBookId(@Param("id") long bookId);
}
