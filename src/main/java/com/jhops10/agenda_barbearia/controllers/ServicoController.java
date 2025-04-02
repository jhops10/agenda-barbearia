package com.jhops10.agenda_barbearia.controllers;

import com.jhops10.agenda_barbearia.entities.Servico;
import com.jhops10.agenda_barbearia.services.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }


    @PostMapping("/servicos")
    public ResponseEntity<Servico> cadastrarServico(@RequestBody Servico servico) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.salvar(servico));
    }

    @GetMapping("/servicos")
    public ResponseEntity<List<Servico>> buscarTodosServicos() {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.listarTodos());
    }

    @GetMapping("/servicos/{id}")
    public ResponseEntity<Servico> buscarServicoPorId(@PathVariable("id") Long id) {
        Servico servico = servicoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(servico);
    }

    @PutMapping("/servicos/{id}")
    public ResponseEntity<Servico> atualizarServicoPorId(@PathVariable("id") Long id, @RequestBody Servico servicoAtualizado) {
        Servico servico = servicoService.atualizar(id, servicoAtualizado);
        return ResponseEntity.status(HttpStatus.OK).body(servico);
    }

    @DeleteMapping("/servicos/{id}")
    public ResponseEntity<Void> deletarServicoPorId(@PathVariable("id") Long id) {
        servicoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/servicos/{idServico}/barbeiro/{idBarbeiro}")
    public ResponseEntity<Servico> associarServicoAoBarbeiro(@PathVariable("idServico") Long idServico, @PathVariable("idBarbeiro") Long idBarbeiro) {
        Servico servicoAtualizado = servicoService.associarBarbeiro(idServico, idBarbeiro);
        return ResponseEntity.status(HttpStatus.OK).body(servicoAtualizado);
    }
}
