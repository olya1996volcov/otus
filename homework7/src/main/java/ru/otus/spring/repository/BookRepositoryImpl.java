package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void updateTitle(long bookId, String newTitle) {

        Query query = em.createQuery("update Book b set b.title = :title where b.id = :id");
        query.setParameter("title", newTitle);
        query.setParameter("id", bookId);
        query.executeUpdate();

    }

    @Transactional
    @Override
    public void delete(long bookId) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", bookId);
        query.executeUpdate();
    }
}
