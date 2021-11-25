package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAllByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.book.id = :book_id", Comment.class);
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void updateTextById(long id, String text) {
        Query query = em.createQuery("update Comment c set c.text = :text where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("text", text);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void delete(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void deleteAllByBookId(long bookId) {
        Query query = em.createQuery("delete from Comment c where c.book.id = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }
}
