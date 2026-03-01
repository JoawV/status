package com.jow.futstatus.backend.repository;

import com.jow.futstatus.backend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubProjections extends JpaRepository<Club, Long> {
}
