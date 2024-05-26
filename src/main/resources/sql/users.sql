DROP TABLE users;

CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(50)  NOT NULL,
    user_role VARCHAR(10)  NOT NULL,
    password  VARCHAR(128) NOT NULL
);
-----------------------------------------------------------------

TRUNCATE TABLE users RESTART IDENTITY CASCADE;

INSERT INTO users(username, user_role, password)
VALUES ('Quinton Bins', 'USER', '{noop}123'),
       ('Gustavo Muller', 'ADMIN', '{bcrypt}$2a$10$fGBUIs9/QtbfwN0NH328jeoaG1sl1sUf6Bh9iESAw1QUwLeWR3lTe'),
       ('Shannan Bahringer', 'MODERATOR', '{noop}123'),
       ('Cory Bechtelar', 'MODERATOR', '{noop}123'),
       ('Lamont Olson', 'USER', '{noop}123'),
       ('Jacqulyn Kohler', 'USER', '{noop}123'),
       ('Jennine Gerhold', 'USER', '{noop}123'),
       ('Kirk Leannon', 'USER', '{noop}123'),
       ('Jose Kulas', 'USER', '{noop}123'),
       ('Fannie Botsford', 'USER', '{noop}123');

SELECT *
FROM users;