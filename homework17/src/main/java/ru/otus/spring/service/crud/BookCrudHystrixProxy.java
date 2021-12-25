package ru.otus.spring.service.crud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.util.ThreadUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCrudHystrixProxy {

    private final BookCrudServiceImpl bookCrudService;

    @HystrixCommand(commandKey = "books", fallbackMethod = "findBookByIdFallback")
    public BookDto findBookById(long bookId) {
        log.info("Before sleep");
        ThreadUtils.sleepRandomly(10000);
        log.info("After sleep");
        return bookCrudService.findBookById(bookId);
    }

    public BookDto findBookByIdFallback(long id) {
        log.info("Fallback method called");
        return new BookDto(0, "", null, null);
    }

}
