package ru.otus.spring.service.crud;

public interface BookCrudService {

    void saveBook(String title, String genreName, String authorName, String authorSurname);

    void showBookById(long id);

    void findAllBooks();

    void updateBookTitleById(long bookId, String newTitle);

    void deleteBookById(long bookId);

}
