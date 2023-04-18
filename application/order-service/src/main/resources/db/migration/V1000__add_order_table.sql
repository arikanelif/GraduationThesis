CREATE TABLE IF NOT EXISTS orders
(
    id                  BIGINT          PRIMARY KEY,
    courier_id          BIGINT,
    complete            BOOLEAN         NOT NULL,
    order_line_items    json
);