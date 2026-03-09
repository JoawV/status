package com.jow.futstatus.backend.dto;

import com.jow.futstatus.backend.model.Club;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClubDTO {
    private Long id;
    private String name;
    private LocalDate foundationDate;
    private String stadium;
    private String city;
    private List<FootballPlayerDTO> footballPlayers;
    private List<ChampionshipDTO> championships;

    public ClubDTO() {
    }

    public ClubDTO(Club entity) {
        BeanUtils.copyProperties(entity, this); // copia da classe "Championship" campos como: id, name

        if (entity.getFootballPlayerList() != null) { // converte a lista de entidades "FootballPlayer" em DTOs
            this.footballPlayers = entity.getFootballPlayerList().stream()
                    .map(FootballPlayerDTO::new)
                    .collect(Collectors.toList());
        }

        if (entity.getChampionshipList() != null) { // converte a lista de entidades "Championship" em DTOs
            this.championships = entity.getChampionshipList().stream()
                    .map(ChampionshipDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
