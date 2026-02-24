package com.jow.futstatus.backend.service;

import com.jow.futstatus.backend.model.FootballPlayer;
import com.jow.futstatus.backend.repository.FootballPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FootballPlayerService {
    private final FootballPlayerRepository footballPlayerRepository;

    public FootballPlayerService(FootballPlayerRepository footballPlayerRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
    }

    public List<FootballPlayer> listarJogador() {
        return footballPlayerRepository.findAll();
    }

    public Optional<FootballPlayer> buscarPorId (Long id) {
        return footballPlayerRepository.findById(id);
    }

    public FootballPlayer salvarJogador(FootballPlayer footballPlayer) {
        return footballPlayerRepository.save(footballPlayer);
    }

    public void deletarJogador(Long id) {
        footballPlayerRepository.deleteById(id);
    }
}
