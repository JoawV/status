package com.jow.futstatus.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

import java.time.Period;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Entity
@Table(name = "football_player")
public class FootballPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @Transient //Não vai criar a coluna "Age" na tabela
    private Integer age;

    @NotNull
    @ManyToMany
    @JoinTable(name = "player_club", 
               joinColumns = @JoinColumn(name = "player_id"), 
               inverseJoinColumns = @JoinColumn(name = "club_id"))
    private List<Club> clubList;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nationality;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String positions;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String foot;

    @NotNull
    @ManyToMany
    @JoinTable(name = "player_championship", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "championship_id"))
    private List<Championship> championshipList;

    public FootballPlayer() {
    }

    public FootballPlayer(Long id, String name, LocalDate birthDate, Integer age, List<Club> clubList, String nationality, String positions, String foot, List<Championship> championshipList) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;
        this.clubList = clubList;
        this.nationality = nationality;
        this.positions = positions;
        this.foot = foot;
        this.championshipList = championshipList;
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

    public void setBirthDate(LocalDate year) {
        this.birthDate = year;
    }

    public Integer getAge() { //Calcula automaticamente a idade. Usa a data de nascimento e a data atual para definir a idade da pessoa
        if (this.birthDate != null) {
            return Period.between(this.birthDate, LocalDate.now()).getYears();
        }
        return null;
    }

    public List<Club> getClubList() { return clubList; }

    public void setClubList(List<Club> clubList) { this.clubList = clubList; }

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

    public List<Championship> getChampionshipList() {
        return championshipList;
    }

    public void setChampionshipList(List<Championship> championshipList) {
        this.championshipList = championshipList;
    }

}
