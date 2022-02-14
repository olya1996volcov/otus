package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.RentedBook;

@Repository
public interface RentedBookRepository extends JpaRepository<RentedBook, Long> {
}
