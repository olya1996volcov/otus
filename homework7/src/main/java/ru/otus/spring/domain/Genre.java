package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "genres")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String genreName;

    @Override
    public String toString() {
        return id + ". " + genreName;
    }
}
