package com.jow.futstatus.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String stadium;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String city;

    @NotNull
    @ManyToMany(mappedBy = "clubList")
    private List<FootballPlayer> footballPlayerList;

    @NotNull
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
}
