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
    id        bigint IDENTITY primary key,
    title     varchar(255),
    genre_id  bigint references genres (id),
    author_id bigint references authors (id),
    is_free   boolean
);


drop table if exists users;
create table users
(
    id      bigint IDENTITY primary key,
    name    varchar(255),
    surname varchar(255)
);

drop table if exists rented_books cascade;
create table rented_books
(
    id      bigint IDENTITY primary key,
    book_id bigint references books (id),
    user_id bigint references users (id)
);

