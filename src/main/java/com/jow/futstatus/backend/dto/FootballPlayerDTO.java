package com.jow.futstatus.backend.dto;

import com.jow.futstatus.backend.model.FootballPlayer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class FootballPlayerDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Integer age;
    private ClubDTO club;
    private String nationality;
    private String positions;
    private String foot;

    private Long clubId;
    private String clubName;
    private List<Long> championshipIds;

    private List<ChampionshipDTO> championships;

    public FootballPlayerDTO() {
    }

    public FootballPlayerDTO(FootballPlayer entity) {
        BeanUtils.copyProperties(entity, this); // copia da classe "FootballPlayer" campos como: id, name, foundationDate

        this.age = entity.getAge();

        if (entity.getClub() != null) {
            this.clubId = entity.getClub().getId();
            this.clubName = entity.getClub().getName();
        }

        if (entity.getChampionshipList() != null) { // converte a lista de entidades "Championship" em DTOs
            this.championships = entity.getChampionshipList().stream()
                    .map(ChampionshipDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
