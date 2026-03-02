package com.jow.futstatus.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    private String stadium;
    private String city;

    @OneToMany(mappedBy = "club")
    private List<FootballPlayer> footballPlayerList;

    @ManyToMany
    @JoinTable(name = "club_championship", joinColumns = @JoinColumn(name = "club_id"), inverseJoinColumns = @JoinColumn(name = "championship_id"))
    private List<Championship> championshipList;

    public Club() {
    }

    public Club(Long id, String name, LocalDate foundationDate, String stadium, String city, List<FootballPlayer> footballPlayerList, List<Championship> championshipList) {
        this.id = id;
        this.name = name;
        this.foundationDate = foundationDate;
        this.stadium = stadium;
        this.city = city;
        this.footballPlayerList = footballPlayerList;
        this.championshipList = championshipList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Club)) return false;
        Club club = (Club) o;
        return id != null && id.equals(club.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
