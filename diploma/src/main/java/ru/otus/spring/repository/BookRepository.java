package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateTitle(@Param("id") long bookId, @Param("title") String newTitle);

}
