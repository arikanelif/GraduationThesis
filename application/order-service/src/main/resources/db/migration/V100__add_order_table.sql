CREATE TABLE IF NOT EXISTS order_
(
    id                  BIGINT          PRIMARY KEY,
    courier_id          BIGINT,
    customer_id         BIGINT,
    order_line_items    json
);