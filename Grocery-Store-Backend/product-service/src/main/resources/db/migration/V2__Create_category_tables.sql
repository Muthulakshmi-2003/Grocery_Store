CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(100) UNIQUE NOT NULL,
                            description VARCHAR(255)
);

ALTER TABLE products
ADD CONSTRAINT fk_category
FOREIGN KEY(category_id)
REFERENCES categories(id);