package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbc;
    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_AUTHOR_QUERY = "insert into authors (`name`, surname) values (?, ?)";

    @Override
    public Author save(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getAuthorName());
            ps.setString(2, author.getAuthorSurname());
            return ps;
        }, keyHolder);

        author.setAuthorId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return author;
    }

    @Override
    public List<Author> findAll() {
        return jdbc.query("select id, `name`, surname from authors ", new AuthorMapper());
    }

    @Override
    public Author findByName(String name, String surname) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        try {
            return jdbc.queryForObject("select id, `name`, surname from authors where name = :name and surname = :surname",
                    params, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {

            return new Author(resultSet.getLong(1), resultSet.getString(2),
                    resultSet.getString(3));
        }
    }
}
