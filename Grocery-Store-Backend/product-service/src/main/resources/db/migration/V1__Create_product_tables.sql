CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          sku VARCHAR(50) UNIQUE NOT NULL,
                          name VARCHAR(150) NOT NULL,
                          description TEXT,
                          price DECIMAL(10,2) NOT NULL,
                          active BOOLEAN NOT NULL DEFAULT TRUE,
                          category_id BIGINT NOT NULL,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);