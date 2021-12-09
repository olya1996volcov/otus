package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.rest.dto.GenreDto;
import ru.otus.spring.util.DtoDomainMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreCrudServiceImpl implements GenreCrudService {

    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public GenreDto saveGenre(GenreDto dto) {
        Genre genre = DtoDomainMapper.toDomainObject(dto);
       return DtoDomainMapper.toDto(genreRepository.save(genre));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GenreDto> showAllGenres() {
        return genreRepository.findAll().stream().map(DtoDomainMapper::toDto)
                .collect(Collectors.toList());
    }
}
