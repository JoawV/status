CREATE TABLE football_player (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    birth_date DATE,
    team VARCHAR(255),
    nationality VARCHAR(255),
    positions TEXT,
    foot VARCHAR(255)
);