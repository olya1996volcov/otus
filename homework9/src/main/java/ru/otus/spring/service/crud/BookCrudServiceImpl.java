package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.StringFormatter;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final BookRepository bookRepository;
    private final ObjectFactory objectFactory;
    private final IOService ioService;
    private final StringFormatter stringFormatter;

    @Transactional
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Book updateBookTitleById(long bookId, String newTitle) {
        Optional<Book> bookOptional = findBookById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(newTitle);
            return bookRepository.save(book);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteBookById(long bookId) {
        bookRepository.deleteById(bookId);
    }
}
