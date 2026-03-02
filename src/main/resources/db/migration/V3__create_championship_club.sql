CREATE TABLE championship (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    year INT NOT NULL,
    img_url VARCHAR(255)
);

CREATE TABLE player_championship (
    player_id BIGINT NOT NULL,
    championship_id BIGINT NOT NULL,
    PRIMARY KEY (player_id, championship_id),
    CONSTRAINT fk_player FOREIGN KEY (player_id) REFERENCES football_player (id) ON DELETE CASCADE,
    CONSTRAINT fk_championship FOREIGN KEY (championship_id) REFERENCES championship (id) ON DELETE CASCADE
);