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
import ru.otus.spring.domain.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    private final NamedParameterJdbcOperations jdbc;
    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_GENRE_QUERY = "insert into genres (`name`) values (?)";

    @Override
    public Genre save(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_GENRE_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, genre.getGenreName());
            return ps;
        }, keyHolder);

        genre.setGenreId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return genre;
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query("select id, `name` from genres ", new GenreMapper());
    }

    @Override
    public Genre findByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        try {
            return jdbc.queryForObject("select id, `name` from genres where name = :name",
                    params, new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Genre(resultSet.getLong(1), resultSet.getString(2));
        }
    }
}
