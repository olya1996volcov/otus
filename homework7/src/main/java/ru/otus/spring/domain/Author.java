package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "authors")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String authorName;

    @Column(name = "surname")
    private String authorSurname;

    @Override
    public String toString() {
        return id + ". " + authorName + ' ' + authorSurname;
    }
}
