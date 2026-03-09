package com.jow.futstatus.backend.controller;

import com.jow.futstatus.backend.dto.FootballPlayerDTO;
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
    public List<FootballPlayerDTO> listarProdutos(){
        return footballPlayerService.listarJogador();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballPlayerDTO> buscarProduto(@PathVariable Long id) {
        return footballPlayerService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FootballPlayerDTO registrarJogador(@RequestBody FootballPlayerDTO dto) {
        return footballPlayerService.salvarJogador(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FootballPlayerDTO> atualizarJogador(@PathVariable Long id, @RequestBody FootballPlayerDTO dto) {
        try {
            FootballPlayerDTO jogadorAtualizado = footballPlayerService.atualizarJogador(id, dto);
            return ResponseEntity.ok(jogadorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogador(@PathVariable Long id) {
        footballPlayerService.deletarJogador(id);
        return ResponseEntity.noContent().build();
    }
}
