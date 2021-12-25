insert into genres (`name`) values ('genre_1');

insert into authors (`name`, surname) values ('author_name_1', 'author_surname_1');
insert into authors (`name`, surname) values ('author_name_2', 'author_surname_2');

insert into books (title, genre_id, author_id) values ('book_name_1', 1, 1);
insert into books (title, genre_id, author_id) values ('book_name_2', 1, 2);

insert into comments (id, text, book_id) values (1, 'text1', 1);

insert into users (id, role, login, password_hash, enabled) values (1, 'ADMIN', 'admin', '$2a$10$lqj04iq3qH7khXJF6x3.YeLF4L1hAUPYaAkau.NLVJjGGV3txPgfu', true); --password = admin
insert into users (id, role, login, password_hash, enabled) values (2, 'USER', 'user', '$2a$10$bYQtsnxTSzqZNn7J2JtJj.j7cF3mjJvPTi6JBMzk65NpVi8XBvch2', true); -- password = user
