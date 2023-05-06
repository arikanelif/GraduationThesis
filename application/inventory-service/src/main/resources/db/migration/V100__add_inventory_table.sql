CREATE TABLE IF NOT EXISTS inventory
(
    id                  BIGINT          PRIMARY KEY,
    sku_code            TEXT            NOT NULL,
    is_in_stock         BOOLEAN         NOT NULL
);