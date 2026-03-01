package com.jow.futstatus.backend.controller;

import com.jow.futstatus.backend.dto.ChampionshipDTO;
import com.jow.futstatus.backend.dto.ClubDTO;
import com.jow.futstatus.backend.service.ChampionshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/campeonatos")
public class ChampionshipController {
    private final ChampionshipService championshipService;

    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping
    public List<ChampionshipDTO> listarCampeonatos(){
        return championshipService.listarCampeonatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipDTO> buscarCampeonatos(@PathVariable Long id) {
        return championshipService.buscarCampeonatoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ChampionshipDTO registrarCampeonatos(@RequestBody ChampionshipDTO dto) {
        return championshipService.salvarCampeonato(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampeonatos(@PathVariable Long id) {
        championshipService.deletarCampeonato(id);
        return ResponseEntity.noContent().build();
    }
}
