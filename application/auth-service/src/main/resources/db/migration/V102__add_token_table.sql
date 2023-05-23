CREATE TABLE IF NOT EXISTS token
(
    id                  BIGINT          generated always as identity primary key,
    token               TEXT,
    token_type          TEXT,
    revoked             BOOLEAN,
    expired             BOOLEAN,
    user_id             BIGINT          NOT NULL
);

