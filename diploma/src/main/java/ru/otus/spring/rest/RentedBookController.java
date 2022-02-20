package ru.otus.spring.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.RentedBookDto;
import ru.otus.spring.service.crud.RentedCrudService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RentedBookController {
    private final RentedCrudService rentedBookService;


    // http://localhost:8080/api/rented_book?bookId=111&userId=222
    @PostMapping(value = "/api/rented_book")
    public RentedBookDto createNewBook(@RequestParam long bookId, @RequestParam long userId) {
        return rentedBookService.saveRentedBook(bookId, userId);
    }

    @GetMapping(value = "/api/rented_book")
    public List<RentedBookDto> getAllBooks() {
        return rentedBookService.findAllRentedBooks();
    }

    @DeleteMapping("/api/rented_book/{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        rentedBookService.deleteRentedBookById(id);
    }

}
