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


    @PostMapping(value = "/api/rented_book")
    public RentedBookDto createNewBook(@RequestBody RentedBookDto dto) {
        return rentedBookService.saveRentedBook(dto);
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
