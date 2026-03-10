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
        mapper.enable(com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
        mapper.enable(com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.INSERT_NULLS_FOR_MISSING_COLUMNS);

        Map<Long, Club> clubMap = new HashMap<>();

        CsvSchema clubSchema = CsvSchema.builder().setUseHeader(true).setColumnSeparator(',').build();
        MappingIterator<Map<String, String>> clubRows = mapper.readerFor(Map.class).with(clubSchema).readValues(new File(clubsPath));
        while (clubRows.hasNext()) {
            saveClubFromRow(clubRows.next(), clubMap);
        }

        CsvSchema playerSchema = CsvSchema.builder().setUseHeader(true).setColumnSeparator(',').build();
        MappingIterator<Map<String, String>> playerRows = mapper.readerFor(Map.class).with(playerSchema).readValues(new File(playersPath));

        while (playerRows.hasNext()) {
            Map<String, String> row = playerRows.next();

            if (row.get("player_name") == null) {
                System.out.println("DEBUG: Row keys received: " + row.keySet());
                System.out.println("DEBUG: Row values: " + row.values());
            }

            savePlayerFromRow(row, clubMap);
        }
    }

    private void saveClubFromRow(Map<String, String> row, Map<Long, Club> clubMap) {
        Club club = new Club();
        club.setName(getTrimmed(row, "club_name"));
        club.setCity(getTrimmed(row, "country_name"));
        club.setStadium(getTrimmed(row, "competition_name"));

        Club savedClub = clubRepository.save(club);

        String idStr = getTrimmed(row, "club_id");
        if (idStr != null && !idStr.isEmpty()) {
            clubMap.put(Long.parseLong(idStr), savedClub);
        }
    }

    private void savePlayerFromRow(Map<String, String> row, Map<Long, Club> clubMap) {
        FootballPlayer player = new FootballPlayer();

        player.setName(row.get("player_name"));
        player.setNationality(row.get("citizenship"));
        player.setPositions(row.get("main_position"));
        player.setFoot(row.get("foot"));

        String dob = row.get("date_of_birth");
        if (dob != null && !dob.isEmpty()) {
            try {
                player.setBirthDate(LocalDate.parse(dob));
            } catch (Exception e) {
            }
        }

        String clubIdStr = row.get("current_club_id");
        if (clubIdStr != null && !clubIdStr.isEmpty()) {
            try {
                Long tmId = Long.parseLong(clubIdStr.trim());
                if (clubMap.containsKey(tmId)) {
                    player.setClub(clubMap.get(tmId));
                }
            } catch (NumberFormatException e) {}
        }
        footballPlayerRepository.save(player);
    }

    private String getTrimmed(Map<String, String> row, String key) {
        return row.entrySet().stream()
                .filter(e -> e.getKey().trim().equalsIgnoreCase(key))
                .map(e -> e.getValue() != null ? e.getValue().trim() : null)
                .findFirst()
                .orElse(null);
    }
}
