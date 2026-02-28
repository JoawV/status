package com.jow.futstatus.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String stadium;
    private List<FootballPlayer> footballPlayerList;
    private List<Championship> championshipList;
}
