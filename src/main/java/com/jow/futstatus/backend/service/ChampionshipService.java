package com.jow.futstatus.backend.service;

import com.jow.futstatus.backend.dto.ChampionshipDTO;
import com.jow.futstatus.backend.model.Championship;
import com.jow.futstatus.backend.repository.ChampionshipRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampionshipService {
    private final ChampionshipRepository championshipRepository;

    public ChampionshipService(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    public List<ChampionshipDTO> listarCampeonatos() {
        List<Championship> players = championshipRepository.findAll();
        return players.stream()
                .map(ChampionshipDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ChampionshipDTO> buscarCampeonatoPorId (Long id) {
        return championshipRepository.findById(id)
                .map(ChampionshipDTO::new);
    }

    public ChampionshipDTO salvarCampeonato(ChampionshipDTO dto) {
        Championship entity = new Championship();
        BeanUtils.copyProperties(dto, entity);

        entity = championshipRepository.save(entity);

        return new ChampionshipDTO(entity);
    }

    public void deletarCampeonato(Long id) {
        championshipRepository.deleteById(id);
    }
}
