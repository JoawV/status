package com.jow.futstatus.backend.model;

import jakarta.persistence.*;
import java.time.Period;
import java.time.LocalDate;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    private String nationality;

    @Column(columnDefinition = "TEXT")
    private String positions;

    private String foot;

    @ManyToMany
    @JoinTable(name = "player_championship", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "championship_id"))
    private List<Championship> championshipList;

    public FootballPlayer() {
    }

    public FootballPlayer(Long id, String name, LocalDate birthDate, Integer age, Club club, String nationality, String positions, String foot, List<Championship> championshipList) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;
        this.club = club;
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

    public Club getClub() { return club; }

    public void setClub(Club club) { this.club = club; }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FootballPlayer that = (FootballPlayer) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getAge(), that.getAge()) && Objects.equals(getClub(), that.getClub()) && Objects.equals(getNationality(), that.getNationality()) && Objects.equals(getPositions(), that.getPositions()) && Objects.equals(getFoot(), that.getFoot()) && Objects.equals(getChampionshipList(), that.getChampionshipList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthDate(), getAge(), getClub(), getNationality(), getPositions(), getFoot(), getChampionshipList());
    }
}
