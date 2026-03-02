package com.jow.futstatus.backend.service;

import com.jow.futstatus.backend.dto.FootballPlayerDTO;
import com.jow.futstatus.backend.model.Club;
import com.jow.futstatus.backend.model.FootballPlayer;
import com.jow.futstatus.backend.repository.ClubRepository;
import com.jow.futstatus.backend.repository.FootballPlayerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FootballPlayerService {
    private final FootballPlayerRepository footballPlayerRepository;
    private final ClubRepository clubRepository;

    public FootballPlayerService(FootballPlayerRepository footballPlayerRepository, ClubRepository clubRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
        this.clubRepository = clubRepository;
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

        if (dto.getClubId() != null) {
            Club club = clubRepository.findById(dto.getClubId())
                    .orElseThrow(() -> new RuntimeException("Club not found with id: " + dto.getClubId()));
            entity.setClub(club);
        }

        entity = footballPlayerRepository.save(entity);

        return new FootballPlayerDTO(entity);
    }

    public void deletarJogador(Long id) {
        footballPlayerRepository.deleteById(id);
    }
}
