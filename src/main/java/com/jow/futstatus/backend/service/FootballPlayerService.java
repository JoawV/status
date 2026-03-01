package com.jow.futstatus.backend.service;

import com.jow.futstatus.backend.dto.FootballPlayerDTO;
import com.jow.futstatus.backend.model.FootballPlayer;
import com.jow.futstatus.backend.repository.FootballPlayerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FootballPlayerService {
    private final FootballPlayerRepository footballPlayerRepository;

    public FootballPlayerService(FootballPlayerRepository footballPlayerRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
    }

    public List<FootballPlayerDTO> listarJogador() {
        List<FootballPlayer> players = footballPlayerRepository.findAll();
        return players.stream()
                .map(FootballPlayerDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<FootballPlayerDTO> buscarPorId (Long id) {
        return footballPlayerRepository.findById(id)
                .map(FootballPlayerDTO::new);
    }

    public FootballPlayerDTO salvarJogador(FootballPlayerDTO dto) {
        FootballPlayer entity = new FootballPlayer();
        BeanUtils.copyProperties(dto, entity);

        entity = footballPlayerRepository.save(entity);

        return new FootballPlayerDTO(entity);
    }

    public void deletarJogador(Long id) {
        footballPlayerRepository.deleteById(id);
    }
}
