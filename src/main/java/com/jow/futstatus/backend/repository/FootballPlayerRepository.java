package com.jow.futstatus.backend.repository;

import com.jow.futstatus.backend.model.FootballPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballPlayerRepository extends JpaRepository<FootballPlayer, Long> {
}