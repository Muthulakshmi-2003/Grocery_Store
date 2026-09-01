
CREATE TABLE IF NOT EXISTS order_items (
 id BIGSERIAL PRIMARY KEY,
 order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_order
    FOREIGN KEY (order_id)
    REFERENCES grocery_orders(id)
    ON DELETE CASCADE
    );