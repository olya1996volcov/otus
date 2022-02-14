drop table if exists authors cascade;
create table authors
(
    id      bigint IDENTITY primary key,
    name    varchar(255),
    surname varchar(255)
);


drop table if exists genres cascade;
create table genres
(
    id   bigint IDENTITY primary key,
    name varchar(255)
);


drop table if exists books cascade;
create table books
(
    id       bigint IDENTITY primary key,
    title    varchar(255),
    genre_id bigint references genres (id),
    author_id bigint references authors (id)
);



drop table if exists comments;
create table comments
(
    id      bigint IDENTITY primary key,
    text    varchar(255),
    book_id bigint references books (id) on delete cascade
);
