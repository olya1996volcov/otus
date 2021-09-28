drop table if exists authors;
create table authors
(
    id      bigint IDENTITY primary key,
    name    varchar2(255),
    surname varchar2(255)
);


drop table if exists genres;
create table genres
(
    id   bigint IDENTITY primary key,
    name varchar2(255)
);


drop table if exists books;
create table books
(
    id       bigint IDENTITY primary key,
    title    varchar2(255),
    genre_id bigint references genres (id),
    author_id bigint references authors (id)
);
