package com.jow.futstatus.backend.service;

import com.jow.futstatus.backend.dto.ClubDTO;
import com.jow.futstatus.backend.model.Club;
import com.jow.futstatus.backend.repository.ClubRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<ClubDTO> listarClubes() {
        List<Club> players = clubRepository.findAll();
        return players.stream()
                .map(ClubDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ClubDTO> buscarClubePorId (Long id) {
        return clubRepository.findById(id)
                .map(ClubDTO::new);
    }

    public ClubDTO salvarClube(ClubDTO dto) {
        Club entity = new Club();
        BeanUtils.copyProperties(dto, entity);

        entity = clubRepository.save(entity);

        return new ClubDTO(entity);
    }

    public void deletarClube(Long id) {
        clubRepository.deleteById(id);
    }
}
