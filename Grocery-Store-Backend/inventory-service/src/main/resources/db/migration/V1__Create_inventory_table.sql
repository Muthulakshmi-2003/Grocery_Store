CREATE TABLE inventory (
                           id BIGSERIAL PRIMARY KEY,
                           product_id BIGINT NOT NULL,
                           available_quantity INT NOT NULL,
                           reserved_quantity INT NOT NULL,
                           updated_at TIMESTAMP
);