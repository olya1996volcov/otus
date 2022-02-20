package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.RentedBook;
import ru.otus.spring.domain.User;
import ru.otus.spring.repository.RentedBookRepository;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.rest.dto.RentedBookDto;
import ru.otus.spring.rest.dto.UserDto;
import ru.otus.spring.util.DtoDomainBookMapper;
import ru.otus.spring.util.DtoDomainRentedBookMapper;
import ru.otus.spring.util.DtoDomainUserMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentedCrudServiceImpl implements RentedCrudService {
    private final RentedBookRepository rentedBookRepository;
    private final BookCrudService bookService;
    private final UserCrudService userService;

    @Transactional
    @Override
    public RentedBookDto saveRentedBook(long bookId, long userId) {
        BookDto bookById = bookService.findBookById(bookId);
        if (!bookById.isFree()) {
            log.warn("Book with id " + bookById.getId() + " isn't free");
            return null;
        }
        bookById.setFree(false);
        UserDto user = userService.findUserById(userId);
        RentedBook book = new RentedBook(0L,
                DtoDomainBookMapper.toDomainObject(bookById),
                DtoDomainUserMapper.toDomainObject(user),
                LocalDate.now());
        RentedBookDto rentedBookDto = DtoDomainRentedBookMapper.toDto(
               (rentedBookRepository.save(book)));
        bookService.updateBookStatus(bookById.getId(), false);
        return rentedBookDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RentedBookDto> findAllRentedBooks() {
        return rentedBookRepository.findAll().stream().map(DtoDomainRentedBookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteRentedBookById(long rentedBookId) {
        rentedBookRepository.deleteById(rentedBookId);
        log.info("Delete book by id = " + rentedBookId + " success!");
        bookService.updateBookStatus(rentedBookId, true);
    }
}
