package com.jow.futstatus.backend.dto;

import com.jow.futstatus.backend.model.FootballPlayer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Getter
@Setter
public class FootballPlayerDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Integer age;
    private String team;
    private String nationality;
    private String positions;
    private String foot;
    private

    public FootballPlayerDTO() {
    }

    public FootballPlayerDTO(FootballPlayer entity) {
        BeanUtils.copyProperties(entity, this); // copia da classe "FootballPlayer" campos como: id, name, foundationDate
    }
}
