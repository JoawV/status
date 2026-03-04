CREATE TABLE club (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    foundation_date DATE,
    stadium VARCHAR(255),
    city VARCHAR(255)
);

CREATE TABLE club_championship (
    club_id BIGINT NOT NULL,
    championship_id BIGINT NOT NULL,
    PRIMARY KEY (club_id, championship_id),
    CONSTRAINT fk_cc_club FOREIGN KEY (club_id) REFERENCES club (id) ON DELETE CASCADE,
    CONSTRAINT fk_cc_championship FOREIGN KEY (championship_id) REFERENCES championship (id) ON DELETE CASCADE
);

ALTER TABLE football_player DROP COLUMN team;
ALTER TABLE football_player ADD COLUMN club_id BIGINT;
ALTER TABLE football_player ADD CONSTRAINT fk_player_club FOREIGN KEY (club_id) REFERENCES club (id) ON DELETE SET NULL;