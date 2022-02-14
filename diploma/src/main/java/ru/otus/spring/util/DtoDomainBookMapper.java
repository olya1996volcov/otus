package ru.otus.spring.util;

import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;

public class DtoDomainBookMapper {

    public static Book toDomainObject(BookDto dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), true);
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(),
                book.getGenre(), book.isFree());
    }

}
