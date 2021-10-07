package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final NamedParameterJdbcOperations jdbc;
    private final JdbcTemplate jdbcTemplate;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private static final String INSERT_BOOK_QUERY = "insert into books (title, genre_id, author_id) values (?, ?, ?)";

    @Override
    public void save(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setLong(2, book.getGenre().getGenreId());
            ps.setLong(3, book.getAuthor().getAuthorId());
            return ps;
        }, keyHolder);

        book.setBookId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public Optional<Book> findById(long id) {
        Book book = jdbc.queryForObject("select b.id, b.title, b.genre_id, b.author_id, g.`name`, a.`name`, a.surname " +
                        "from books b left join genres g on b.genre_id = g.id" +
                        " left join authors a on b.author_id = a.id where b.id=:id ",
                Map.of("id", id), new BookMapper());

        if (book != null) {
            return Optional.of(book);
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        return jdbc.query("select b.id, b.title, b.genre_id, b.author_id, g.`name`, a.`name`, a.surname " +
                "from books b left join genres g on b.genre_id = g.id" +
                " left join authors a on b.author_id = a.id", new BookMapper());

    }

    @Override
    public void updateTitle(long bookId, String newTitle) {
        String updateTitleSql = "update books set title=:title where id=:id";
        jdbc.update(updateTitleSql, Map.of("id", bookId, "title", newTitle));
    }

    @Override
    public void delete(long bookId) {
        jdbc.update("delete from books where id=:id", Map.of("id", bookId));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(resultSet.getLong(1), resultSet.getString(2),
                    new Author(resultSet.getLong(4), resultSet.getString(6), resultSet.getString(7)),
                    new Genre(resultSet.getLong(3), resultSet.getString(5)));
        }
    }
}
