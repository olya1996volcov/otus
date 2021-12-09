package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.domain.Book;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {
    private static final Logger log = LoggerFactory.getLogger( BookCrudServiceImpl.class );

    private RestOperations rest = new RestTemplate();

    @Override
    public Book showBookById(long id) {
        return rest.getForObject( "http://localhost:8080/api/book/{id}", Book.class, Collections.singletonMap("id", id));
    }

}
