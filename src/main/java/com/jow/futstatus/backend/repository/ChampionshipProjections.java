package com.jow.futstatus.backend.repository;

import com.jow.futstatus.backend.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipProjections extends JpaRepository<Championship, Long> {
}
