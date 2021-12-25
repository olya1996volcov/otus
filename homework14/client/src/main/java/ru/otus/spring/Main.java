package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookCrudService;

@SpringBootApplication
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        BookCrudService service = ctx.getBean(BookCrudService.class);
        Book book = service.showBookById(1L);

        log.info("get book: " + book.getAuthor().getAuthorName() + " " +
                book.getAuthor().getAuthorSurname() + ". " + book.getTitle());
    }
}
