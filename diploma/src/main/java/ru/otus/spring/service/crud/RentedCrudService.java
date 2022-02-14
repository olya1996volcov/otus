package ru.otus.spring.service.crud;

import ru.otus.spring.rest.dto.RentedBookDto;

import java.util.List;

public interface RentedCrudService {

    RentedBookDto saveRentedBook(RentedBookDto book);

    List<RentedBookDto> findAllRentedBooks();

    void deleteRentedBookById(long bookId);
}
