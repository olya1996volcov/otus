package ru.otus.spring.util;

import ru.otus.spring.domain.RentedBook;
import ru.otus.spring.rest.dto.RentedBookDto;

public class DtoDomainRentedBookMapper {

    public static RentedBookDto toDto(RentedBook rentedBook) {
        return new RentedBookDto(rentedBook.getId(), rentedBook.getBook(), rentedBook.getUser(), rentedBook.getRentDate());
    }

    public static RentedBook toDomainObject(RentedBookDto dto) {
        return new RentedBook(dto.getId(), dto.getBook(), dto.getUser(), dto.getRentDate());
    }

}
