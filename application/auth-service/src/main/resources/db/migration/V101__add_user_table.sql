CREATE TABLE IF NOT EXISTS user
(
    id                  BIGINT          PRIMARY KEY,
    first_name          TEXT            NOT NULL,
    last_name           TEXT            NOT NULL,
    email               TEXT            NOT NULL,
    password            TEXT            NOT NULL,
    phone_number        TEXT            NOT NULL,
    address_id          TEXT            NOT NULL,
);