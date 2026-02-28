package com.jow.futstatus.backend.dto;

import com.jow.futstatus.backend.model.Championship;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ChampionshipDTO {
    private Long id;
    private String name;
    private int year;
    private String imgUrl;

    public ChampionshipDTO() {
    }

    public ChampionshipDTO(Championship entity) {
        BeanUtils.copyProperties(entity, this); // copia da classe "Championship" campos como: id, name
    }
}
