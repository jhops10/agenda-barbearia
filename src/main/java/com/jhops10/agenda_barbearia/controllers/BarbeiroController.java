package com.jhops10.agenda_barbearia.controllers;

import com.jhops10.agenda_barbearia.entities.Barbeiro;
import com.jhops10.agenda_barbearia.services.BarbeiroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    public BarbeiroController(BarbeiroService barbeiroService) {
        this.barbeiroService = barbeiroService;
    }

    @PostMapping("/barbeiros")
    public ResponseEntity<Barbeiro> cadastrarBarbeiro(@RequestBody Barbeiro barbeiro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(barbeiroService.salvar(barbeiro));
    }

    @GetMapping("/barbeiros")
    public ResponseEntity<List<Barbeiro>> buscarTodosBarbeiros() {
        return ResponseEntity.status(HttpStatus.OK).body(barbeiroService.listarTodos());
    }

    @GetMapping("/barbeiros/{id}")
    public ResponseEntity<Barbeiro> buscarBarbeiroPorId(@PathVariable("id") Long id) {
        Barbeiro barbeiro = barbeiroService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(barbeiro);
    }

    @PutMapping("/barbeiros/{id}")
    public ResponseEntity<Barbeiro> atualizarBarbeiro(@PathVariable("id") Long id, @RequestBody Barbeiro barbeiroAtualizado) {
        Barbeiro barbeiro = barbeiroService.atualizar(id, barbeiroAtualizado);
        return ResponseEntity.status(HttpStatus.OK).body(barbeiro);
    }

    @DeleteMapping("/barbeiros/{id}")
    public ResponseEntity<Void> deletarBarbeiroPorId(@PathVariable("id") Long id) {
        barbeiroService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
