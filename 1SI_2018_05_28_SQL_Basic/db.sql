DROP DATABASE codecool_application;
DROP TABLE mentors;
DROP TABLE applicants;

CREATE DATABASE codecool_application;

\c codecool_application;

CREATE TABLE mentors (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(40),
    last_name VARCHAR(40),
    nick_name VARCHAR(40),
    phone_number TEXT,
    email VARCHAR(60),
    city VARCHAR(40),
    favourite_number INTEGER
);

CREATE TABLE applicants (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(40),
    last_name VARCHAR(40),
    phone_number TEXT,
    email VARCHAR(60),
    application_code INTEGER
);