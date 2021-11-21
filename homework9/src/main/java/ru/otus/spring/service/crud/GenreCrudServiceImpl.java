package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.StringFormatter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {

    private final IOService ioService;
    private final GenreRepository genreRepository;
    private final StringFormatter stringFormatter;

    @Transactional
    @Override
    public Genre saveGenre(Genre genre) {
       return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> showAllGenres() {
        return genreRepository.findAll();
    }
}
