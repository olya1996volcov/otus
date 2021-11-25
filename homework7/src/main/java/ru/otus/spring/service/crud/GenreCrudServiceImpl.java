package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.service.IOService;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {

    private final IOService ioService;
    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public void saveGenre(String name) {
        Genre genre = Genre.builder()
                .genreName(name)
                .build();
        genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllGenres() {
        genreRepository.findAll().forEach(genre -> ioService.printString(genre.toString()));
    }
}
