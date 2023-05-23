CREATE TABLE IF NOT EXISTS user_
(
    id                  BIGINT          generated always as identity primary key,
    firstname          TEXT,
    lastname           TEXT,
    email               TEXT            NOT NULL,
    password            TEXT            NOT NULL,
    phone_number        TEXT,
    address_id          TEXT
);