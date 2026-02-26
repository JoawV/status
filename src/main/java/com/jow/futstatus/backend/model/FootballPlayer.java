package com.jow.futstatus.backend.model;

import jakarta.persistence.*;
import java.time.Period;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "football_player")
public class FootballPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Transient //Não vai criar a coluna "Age" na tabela
    private Integer age;

    private String team;
    private String nationality;

    @Column(columnDefinition = "TEXT")
    private String positions;

    private String foot;

    public FootballPlayer() {
    }

    public FootballPlayer(Long id, String name, LocalDate birthDate, String team, String nationality, String positions, String foot) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.team = team;
        this.nationality = nationality;
        this.positions = positions;
        this.foot = foot;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FootballPlayer that = (FootballPlayer) o;
        return getAge() == that.getAge() && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getTeam(), that.getTeam()) && Objects.equals(getNationality(), that.getNationality()) && Objects.equals(getPositions(), that.getPositions()) && Objects.equals(getFoot(), that.getFoot());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthDate(), getAge(), getTeam(), getNationality(), getPositions(), getFoot());
    }
}
