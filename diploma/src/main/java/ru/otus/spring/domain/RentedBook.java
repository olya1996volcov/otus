package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Table(name = "rented_books")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Fetch(FetchMode.JOIN)
    @OneToOne(targetEntity = Book.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @Fetch(FetchMode.JOIN)
    @OneToOne(targetEntity = Genre.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
