
DROP TABLE entries;

\c guestbook;

CREATE TABLE entries (
    id SERIAL PRIMARY KEY,
    nick_name VARCHAR(40),
    user_message VARCHAR(200),
    message_date VARCHAR(40)
);