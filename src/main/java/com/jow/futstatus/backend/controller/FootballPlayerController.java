package com.jow.futstatus.backend.controller;

import com.jow.futstatus.backend.model.FootballPlayer;
import com.jow.futstatus.backend.service.FootballPlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
public class FootballPlayerController {
    private final FootballPlayerService footballPlayerService;

    public FootballPlayerController(FootballPlayerService footballPlayerService) {
        this.footballPlayerService = footballPlayerService;
    }

    @GetMapping
    public List<FootballPlayer> listarProdutos(){
        return footballPlayerService.listarJogador();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballPlayer> buscarProduto(@PathVariable Long id) {
        return footballPlayerService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FootballPlayer registrarJogador(@RequestBody FootballPlayer footballPlayer) {
        return footballPlayerService.salvarJogador(footballPlayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogador(@PathVariable Long id) {
        footballPlayerService.deletarJogador(id);
        return ResponseEntity.noContent().build();
    }
}
