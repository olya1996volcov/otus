insert into genres (id, `name`) values (11, 'genre_1');

insert into authors (id, `name`, surname) values (22, 'author_name_2', 'author_surname_2');

insert into books (id, title, genre_id, author_id) values (111, 'book_name_1', 11, 22);

insert into comments (id, text, book_id) values (1, 'Good book!', 111);