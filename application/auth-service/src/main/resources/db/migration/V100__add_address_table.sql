CREATE TABLE IF NOT EXISTS address
(
    id                  BIGINT          PRIMARY KEY,
    street              TEXT            NOT NULL,
    city                TEXT            NOT NULL,
    states              TEXT            NOT NULL,
    open_address        TEXT            NOT NULL,
    country             TEXT            NOT NULL
);