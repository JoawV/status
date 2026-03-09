package com.jow.futstatus.backend.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jow.futstatus.backend.model.Club;
import com.jow.futstatus.backend.model.FootballPlayer;
import com.jow.futstatus.backend.repository.ClubRepository;
import com.jow.futstatus.backend.repository.FootballPlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataImportService {
    private final ClubRepository clubRepository;
    private final FootballPlayerRepository footballPlayerRepository;

    public DataImportService(ClubRepository clubRepository, FootballPlayerRepository footballPlayerRepository) {
        this.clubRepository = clubRepository;
        this.footballPlayerRepository = footballPlayerRepository;
    }

    @Transactional
    public void importFromTransfermarkt(String clubsPath, String playersPath) throws Exception {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader(); // This allows us to skip columns in the CSV that aren't in our model

        Map<Long, Club> clubMap = new HashMap<>(); // Maps Transfermarkt ID -> Our Database Entity

        MappingIterator<Map<String, String>> clubRows = mapper.readerFor(Map.class).with(schema).readValues(new File(clubsPath));

        while (clubRows.hasNext()) {
            Map<String, String> row = clubRows.next();
            Club club = new Club();
            club.setName(row.get("club_name"));
            club.setStadium(row.get("stadium_name"));
            club.setCity("country_name"); // The CSV doesn't have city, so we use stadium name or leave null

            Club savedClub = clubRepository.save(club); // Save the link: Transfermarkt ID -> Our Saved Club
            clubMap.put(Long.parseLong(row.get("club_id")), savedClub);
        }

        // 2. IMPORT PLAYERS
        MappingIterator<Map<String, String>> playerRows = mapper.readerFor(Map.class).with(schema).readValues(new File(playersPath));

        while (playerRows.hasNext()) {
            Map<String, String> row = playerRows.next();
            FootballPlayer player = new FootballPlayer();
            player.setName(row.get("player_name"));
            player.setNationality(row.get("citizenship"));
            player.setPositions(row.get("main_position"));
            player.setFoot(row.get("foot"));

            // Handle Date
            String dob = row.get("date_of_birth");
            if (dob != null && !dob.isEmpty()) {
                player.setBirthDate(LocalDate.parse(dob));
            }

            // LINK TO CLUB
            String clubIdStr = row.get("current_club_id");
            if (clubIdStr != null) {
                Long tmClubId = Long.parseLong(clubIdStr);
                if (clubMap.containsKey(tmClubId)) {
                    player.setClub(clubMap.get(tmClubId));
                }
            }
            footballPlayerRepository.save(player);
        }
    }
}
