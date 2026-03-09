package com.jow.futstatus.backend.controller;

import com.jow.futstatus.backend.dto.ClubDTO;
import com.jow.futstatus.backend.service.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubes")
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubDTO> listarClubes(){
        return clubService.listarClubes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> buscarClube(@PathVariable Long id) {
        return clubService.buscarClubePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClubDTO registrarClube(@RequestBody ClubDTO dto) {
        return clubService.salvarClube(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClube(@PathVariable Long id) {
        clubService.deletarClube(id);
        return ResponseEntity.noContent().build();
    }
}
