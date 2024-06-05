DROP TABLE customers;

CREATE TABLE customers
(
    id        SERIAL PRIMARY KEY,
    customer_name  VARCHAR(50)  NOT NULL,
    customer_role VARCHAR(10)  NOT NULL
);
-----------------------------------------------------------------

TRUNCATE TABLE customers RESTART IDENTITY CASCADE;

INSERT INTO customers(customer_name, customer_role)
VALUES ('Quinton Bins', 'USER'),
       ('Gustavo Muller', 'ADMIN'),
       ('Shannan Bahringer', 'MODERATOR'),
       ('Cory Bechtelar', 'MODERATOR'),
       ('Lamont Olson', 'USER'),
       ('Jacqulyn Kohler', 'USER'),
       ('Jennine Gerhold', 'USER'),
       ('Kirk Leannon', 'USER'),
       ('Jose Kulas', 'USER'),
       ('Fannie Botsford', 'USER');

SELECT *
FROM customers;