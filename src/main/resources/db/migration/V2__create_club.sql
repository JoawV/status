CREATE TABLE club (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    birth_date DATE,
    stadium VARCHAR(255),
    city VARCHAR(255),
    positions TEXT,
    foot VARCHAR(255)
);