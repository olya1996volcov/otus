insert into genres (`name`) values ('genre_1');

insert into authors (`name`, surname) values ('author_name_1', 'author_surname_1');
insert into authors (`name`, surname) values ('author_name_2', 'author_surname_2');

insert into books (title, genre_id, author_id) values ('book_name_1', 1, 1);
insert into books (title, genre_id, author_id) values ('book_name_2', 1, 2);

insert into comments (id, text, book_id) values (1, 'text1', 1);
