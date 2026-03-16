-- Create player_club many-to-many relationship table
CREATE TABLE player_club (
    player_id BIGINT NOT NULL,
    club_id BIGINT NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (player_id, club_id),
    CONSTRAINT fk_pc_player FOREIGN KEY (player_id) REFERENCES football_player (id) ON DELETE CASCADE,
    CONSTRAINT fk_pc_club FOREIGN KEY (club_id) REFERENCES club (id) ON DELETE CASCADE
);

-- Remove the old one-to-many relationship
ALTER TABLE football_player DROP CONSTRAINT fk_player_club;
ALTER TABLE football_player DROP COLUMN club_id;

-- Add indexes for better performance
CREATE INDEX idx_player_club_player_id ON player_club(player_id);
CREATE INDEX idx_player_club_club_id ON player_club(club_id);
CREATE INDEX idx_club_name ON club(name);
CREATE INDEX idx_football_player_nationality ON football_player(nationality);
CREATE INDEX idx_football_player_positions ON football_player(positions);
