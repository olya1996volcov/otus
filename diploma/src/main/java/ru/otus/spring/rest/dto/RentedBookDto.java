package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.User;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentedBookDto {

    private long id;
    private Book book;
    private User user;
    private LocalDate rentDate;
}
