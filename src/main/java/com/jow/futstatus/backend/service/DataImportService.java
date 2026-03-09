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

        CsvSchema schema = CsvSchema.emptySchema()
                .withHeader()
                .withColumnSeparator(';')
                .withColumnReordering(true);

        Map<Long, Club> clubMap = new HashMap<>();

        MappingIterator<Map<String, String>> clubRows = mapper.readerFor(Map.class)
                .with(schema).readValues(new File(clubsPath));

        if (clubRows.hasNext()) {
            Map<String, String> firstRow = clubRows.next();
            System.out.println("DEBUG - First Club Row Content: " + firstRow); // This is key!
            saveClubFromRow(firstRow, clubMap);
        }

        while (clubRows.hasNext()) {
            saveClubFromRow(clubRows.next(), clubMap);
        }

        MappingIterator<Map<String, String>> playerRows = mapper.readerFor(Map.class)
                .with(schema).readValues(new File(playersPath));

        if (playerRows.hasNext()) {
            Map<String, String> firstRow = playerRows.next();
            System.out.println("DEBUG - First Player Row Content: " + firstRow);
            savePlayerFromRow(firstRow, clubMap);
        }

        while (playerRows.hasNext()) {
            savePlayerFromRow(playerRows.next(), clubMap);
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
        player.setName(getTrimmed(row, "player_name"));
        player.setNationality(getTrimmed(row, "citizenship"));
        player.setPositions(getTrimmed(row, "main_position"));
        player.setFoot(getTrimmed(row, "foot"));

        String dob = getTrimmed(row, "date_of_birth");
        if (dob != null && !dob.isEmpty()) {
            try { player.setBirthDate(LocalDate.parse(dob)); } catch (Exception e) {}
        }

        String clubIdStr = getTrimmed(row, "current_club_id");
        if (clubIdStr != null && !clubIdStr.isEmpty()) {
            Long tmId = Long.parseLong(clubIdStr);
            if (clubMap.containsKey(tmId)) {
                player.setClub(clubMap.get(tmId));
            }
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
