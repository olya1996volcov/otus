package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
@Builder
public class Author {
    @Setter
    private long authorId;
    private final String authorName;
    private final String authorSurname;

    @Override
    public String toString() {
        return authorId + ". " + authorName + ' ' + authorSurname;
    }
}
