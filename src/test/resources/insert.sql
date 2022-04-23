INSERT INTO post(id, title, content, createdAt, editedAt , publish)
VALUES(1, 'first post', 'the first post content', '2010-07-22 13:05:58.0', '2010-07-22 13:05:58.0',1);

INSERT INTO post(id, title, content, createdAt, editedAt , publish)
VALUES(2, 'second post', 'the first post content', '2010-07-22 13:05:58.0', '2010-07-22 13:05:58.0',1);

INSERT INTO post(id, title, content, createdAt, editedAt , publish)
VALUES(3, 'second post', 'the second post content', '2010-07-22 13:05:58.0', '2010-07-22 13:05:58.0',1);

INSERT INTO comment(id, content, rate , post_id)
VALUES(1, 'first post', 5, 1);

INSERT INTO comment(id, content, rate , post_id)
VALUES(2, 'first post', 5, 1);

INSERT INTO comment(id, content, rate , post_id)
VALUES(3, 'first post', 5, 1);

INSERT INTO comment(id, content, rate , post_id)
VALUES(4, 'first post', 3, 2);

INSERT INTO comment(id, content, rate , post_id)
VALUES(5, 'first post', 2, 2);

INSERT INTO comment(id, content, rate , post_id)
VALUES(6, 'first post', 1, 2);

INSERT INTO roles(id, name)
VALUES(1, 'User');

INSERT INTO roles(id, name)
VALUES(2, 'Admin');
