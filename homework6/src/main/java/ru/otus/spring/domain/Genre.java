package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
@Builder
public class Genre {
    @Setter
    private long genreId;

    private final String genreName;

    @Override
    public String toString() {
        return genreId + ". " + genreName;
    }
}
