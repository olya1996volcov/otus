package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.RentedBook;
import ru.otus.spring.repository.RentedBookRepository;
import ru.otus.spring.rest.RentedBookController;
import ru.otus.spring.rest.dto.RentedBookDto;
import ru.otus.spring.util.DtoDomainAuthorMapper;
import ru.otus.spring.util.DtoDomainRentedBookMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentedCrudServiceImpl implements RentedCrudService {
    private final RentedBookRepository rentedBookRepository;

    @Transactional
    @Override
    public RentedBookDto saveRentedBook(RentedBookDto dto) {
        RentedBook rentedBook = DtoDomainRentedBookMapper.toDomainObject(dto);
        //todo проверить что бука свободна, обновить буку
        return DtoDomainRentedBookMapper.toDto(rentedBookRepository.save(rentedBook));
    }

    @Transactional(readOnly = true)
    @Override
    public List<RentedBookDto> findAllRentedBooks() {
        return rentedBookRepository.findAll().stream().map(DtoDomainRentedBookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRentedBookById(long rentedBookId) {
        //todo обновить буку
        rentedBookRepository.deleteById(rentedBookId);
    }
}
