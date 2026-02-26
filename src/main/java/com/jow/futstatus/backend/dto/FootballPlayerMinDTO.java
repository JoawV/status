package com.jow.futstatus.backend.dto;

import com.jow.futstatus.backend.model.FootballPlayer;
import com.jow.futstatus.backend.projections.FootballPlayerMinProjections;
import org.springframework.beans.BeanUtils;

public class FootballPlayerMinDTO {
    private Long id;
    private String name;
    private String team;
    private String nationality;
    private String positions;

    public FootballPlayerMinDTO() {
    }

    public FootballPlayerMinDTO(FootballPlayer entity) {
        id = entity.getId();
        name = entity.getName();
        team = entity.getTeam();
        nationality = entity.getNationality();
        positions = entity.getPositions();
    }

    public FootballPlayerMinDTO(FootballPlayerMinProjections projection) {
        id = projection.getId();
        name = projection.getName();
        team = projection.getTeam();
        nationality = projection.getNationality();
        positions = projection.getPositions();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }
}
