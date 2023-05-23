CREATE TABLE IF NOT EXISTS basket_
(
    id                  BIGINT generated always as identity primary key,
    customer_id         BIGSERIAL,
    order_line_items    VARCHAR
);