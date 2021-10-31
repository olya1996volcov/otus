package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final BookRepository bookRepository;
    private final ObjectFactory objectFactory;
    private final IOService ioService;

    @Transactional
    @Override
    public void saveBook(String title, String genreName, String authorName, String authorSurname) {
        Book book = objectFactory.createBook(title, authorName, authorSurname, genreName);
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public void showBookById(long id) {
        bookRepository.findById(id).ifPresent(book -> ioService.printString(book.toString()));
    }

    @Transactional(readOnly = true)
    @Override
    public void findAllBooks() {
        bookRepository.findAll().forEach(book -> ioService.printString(book.toString()));
    }

    @Transactional
    @Override
    public void updateBookTitleById(long bookId, String newTitle) {
        bookRepository.updateTitle(bookId, newTitle);
    }

    @Transactional
    @Override
    public void deleteBookById(long bookId) {
        bookRepository.delete(bookId);
    }
}
