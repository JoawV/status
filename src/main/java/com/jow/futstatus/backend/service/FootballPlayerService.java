package com.jow.futstatus.backend.service;

import com.jow.futstatus.backend.dto.FootballPlayerDTO;
import com.jow.futstatus.backend.model.Championship;
import com.jow.futstatus.backend.model.Club;
import com.jow.futstatus.backend.model.FootballPlayer;
import com.jow.futstatus.backend.repository.ChampionshipRepository;
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
    private final ChampionshipRepository championshipRepository;

    public FootballPlayerService(FootballPlayerRepository footballPlayerRepository, ClubRepository clubRepository, ChampionshipRepository championshipRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
        this.clubRepository = clubRepository;
        this.championshipRepository = championshipRepository;
    }

    public List<FootballPlayerDTO> listarJogador() {
        List<FootballPlayer> players = footballPlayerRepository.findAll();
        return players.stream()
                .map(FootballPlayerDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<FootballPlayerDTO> buscarPorId (Long id) {
        return footballPlayerRepository.findById(id).map(FootballPlayerDTO::new);
    }

    public FootballPlayerDTO salvarJogador(FootballPlayerDTO dto) {
        FootballPlayer entity = new FootballPlayer();
        BeanUtils.copyProperties(dto, entity, "id");

        if (dto.getChampionshipIds() != null && !dto.getChampionshipIds().isEmpty()) {
            List<Championship> championships = championshipRepository.findAllById(dto.getChampionshipIds());
            entity.setChampionshipList(championships);
        }

        entity = footballPlayerRepository.save(entity);

        return new FootballPlayerDTO(entity);
    }

    public FootballPlayerDTO atualizarJogador(Long id, FootballPlayerDTO dto) {
        FootballPlayer entity = footballPlayerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o ID: " + id));

        BeanUtils.copyProperties(dto, entity, "id");

        vincularRelacionamentos(dto, entity);

        entity = footballPlayerRepository.save(entity);
        return new FootballPlayerDTO(entity);
    }

    public void deletarJogador(Long id) {
        footballPlayerRepository.deleteById(id);
    }

    private void vincularRelacionamentos(FootballPlayerDTO dto, FootballPlayer entity) {
        if (dto.getClubId() != null) {
            Club club = clubRepository.findById(dto.getClubId())
                    .orElseThrow(() -> new RuntimeException("Clube não encontrado"));
            entity.setClubList(List.of(club));
        } else {
            entity.setClubList(null);
        }

        if (dto.getChampionshipIds() != null && !dto.getChampionshipIds().isEmpty()) {
            List<Championship> championships = championshipRepository.findAllById(dto.getChampionshipIds());
            entity.setChampionshipList(championships);
        } else {
            entity.setChampionshipList(null);
        }
    }
}
