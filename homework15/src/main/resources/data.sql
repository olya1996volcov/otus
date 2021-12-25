insert into genres (id,name) values (1,'genre_1');

insert into authors (id,name, surname) values (1,'author_name_1', 'author_surname_1');
insert into authors (id,name, surname) values (2,'author_name_2', 'author_surname_2');

insert into books (id,title, genre_id, author_id) values (1,'book_name_1', 1, 1);
insert into books (id,title, genre_id, author_id) values (2,'book_name_2', 1, 2);

insert into comments (id, text, book_id) values (1, 'text1', 1);
