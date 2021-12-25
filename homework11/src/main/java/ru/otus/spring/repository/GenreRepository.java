package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("select g from Genre g where g.genreName = :genreName")
    Genre findByName(@Param("genreName") String name);
}
