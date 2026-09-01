CREATE TABLE IF NOT EXISTS grocery_orders (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    order_total DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL
    );