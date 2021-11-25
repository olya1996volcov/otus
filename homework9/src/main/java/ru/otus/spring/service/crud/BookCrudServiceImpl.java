package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.rest.NotFoundException;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.util.DtoDomainMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final BookRepository bookRepository;


    @Transactional
    @Override
    public BookDto saveBook(BookDto dto) {
        Book book = DtoDomainMapper.toDomainObject(dto);
        return DtoDomainMapper.toDto(bookRepository.save(book));
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto findBookById(long id) {
        return DtoDomainMapper.toDto(bookRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAllBooks() {
        return bookRepository.findAll().stream().map(DtoDomainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookDto updateBookTitleById(long bookId, String newTitle) {
        BookDto book = findBookById(bookId);
        book.setTitle(newTitle);
        Book book1 = DtoDomainMapper.toDomainObject(book);
        return DtoDomainMapper.toDto(bookRepository.save(book1));
    }

    @Transactional
    @Override
    public void deleteBookById(long bookId) {
        bookRepository.deleteById(bookId);
    }
}
