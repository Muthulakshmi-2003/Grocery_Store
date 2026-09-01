CREATE TABLE stock_movements (
                                 id BIGSERIAL PRIMARY KEY,
                                 product_id BIGINT NOT NULL,
                                 movement_type VARCHAR(30),
                                 quantity INT,
                                 reference_id VARCHAR(100),
                                 created_at TIMESTAMP
);
ALTER TABLE inventory
    ADD CONSTRAINT fk_stock_movement
        FOREIGN KEY(stock_movement_id)
            REFERENCES stock_movements(id);