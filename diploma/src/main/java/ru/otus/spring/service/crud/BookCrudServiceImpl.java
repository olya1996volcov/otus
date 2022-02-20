package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.rest.NotFoundException;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.util.DtoDomainBookMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final BookRepository bookRepository;


    @Transactional
    @Override
    public BookDto saveBook(BookDto dto) {
        Book book = DtoDomainBookMapper.toDomainObject(dto);
        return DtoDomainBookMapper.toDto(bookRepository.save(book));
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto findBookById(long id) {
        return DtoDomainBookMapper.toDto(bookRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAllBooks() {
        return bookRepository.findAll().stream().map(DtoDomainBookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookDto updateBookTitleById(long bookId, String newTitle) {
        BookDto bookDto = findBookById(bookId);
        bookDto.setTitle(newTitle);
        Book book = DtoDomainBookMapper.toDomainObject(bookDto);
        return DtoDomainBookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    @Override
    public void deleteBookById(long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookDto updateBookStatus(long bookId, boolean isFree) {
        BookDto bookDto = findBookById(bookId);
        bookDto.setFree(isFree);
        Book book = DtoDomainBookMapper.toDomainObject(bookDto);
        return DtoDomainBookMapper.toDto(bookRepository.save(book));
    }
}
