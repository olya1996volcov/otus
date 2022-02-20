insert into genres (id,name) values (1,'genre_1');

insert into authors (id,name, surname) values (1,'author_name_1', 'author_surname_1');
insert into authors (id,name, surname) values (2,'author_name_2', 'author_surname_2');

insert into books (id,title, genre_id, author_id, is_free) values (1,'book_name_1', 1, 1, true);
insert into books (id,title, genre_id, author_id, is_free) values (2,'book_name_2', 1, 2, true);

insert into users (id, login, password_hash, role) values (1, 'user', '$2a$10$bYQtsnxTSzqZNn7J2JtJj.j7cF3mjJvPTi6JBMzk65NpVi8XBvch2', 'USER'); -- password = user
insert into users (id, login, password_hash, role) values (2, 'admin', '$2a$10$lqj04iq3qH7khXJF6x3.YeLF4L1hAUPYaAkau.NLVJjGGV3txPgfu', 'ADMIN'); --password = admin



