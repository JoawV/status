package com.jow.futstatus.backend.dto;

import com.jow.futstatus.backend.model.FootballPlayer;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class FootballPlayerDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Integer age;
    private String team;
    private String nationality;
    private String positions;
    private String foot;

    public FootballPlayerDTO() {
    }

    public FootballPlayerDTO(FootballPlayer entity) {
        BeanUtils.copyProperties(entity, this);
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }
}
