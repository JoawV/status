package com.jow.futstatus.backend.service;

import com.jow.futstatus.backend.dto.ChampionshipDTO;
import com.jow.futstatus.backend.dto.ClubDTO;
import com.jow.futstatus.backend.model.Championship;
import com.jow.futstatus.backend.model.Club;
import com.jow.futstatus.backend.repository.ChampionshipProjections;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChampionshipService {
    private final ChampionshipProjections championshipProjections;

    public ChampionshipService(ChampionshipProjections championshipProjections) {
        this.championshipProjections = championshipProjections;
    }

    public List<ChampionshipDTO> listarCampeonatos() {
        List<Championship> players = championshipProjections.findAll();
        return players.stream()
                .map(ChampionshipDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ChampionshipDTO> buscarCampeonatoPorId (Long id) {
        return championshipProjections.findById(id)
                .map(ChampionshipDTO::new);
    }

    public ChampionshipDTO salvarCampeonato(ChampionshipDTO dto) {
        Championship entity = new Championship();
        BeanUtils.copyProperties(dto, entity);

        entity = championshipProjections.save(entity);

        return new ChampionshipDTO(entity);
    }

    public void deletarCampeonato(Long id) {
        championshipProjections.deleteById(id);
    }
}
