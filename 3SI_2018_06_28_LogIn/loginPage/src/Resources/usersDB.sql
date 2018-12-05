DROP TABLE usersCC;

\c usersDB;

CREATE TABLE usersCC (
    id SERIAL PRIMARY KEY,
    name_surname VARCHAR(200),
    user_name VARCHAR(40),
    password VARCHAR(200)
);