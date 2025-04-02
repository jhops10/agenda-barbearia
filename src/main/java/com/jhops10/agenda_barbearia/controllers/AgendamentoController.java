package com.jhops10.agenda_barbearia.controllers;

import com.jhops10.agenda_barbearia.dto.AgendamentoRequestDTO;
import com.jhops10.agenda_barbearia.entities.Agendamento;
import com.jhops10.agenda_barbearia.services.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping("/agendamentos")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody @Valid AgendamentoRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.salvar(requestDTO));
    }

    @GetMapping("/agendamentos")
    public ResponseEntity<List<Agendamento>> buscarTodosAgendamentos() {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.listarTodos());
    }

    @GetMapping("/agendamentos/{id}")
    public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.buscarPorId(id));
    }

    @GetMapping("/agendamentos/barbeiro/{idBarbeiro}")
    public ResponseEntity<List<Agendamento>> buscarAgendamentoPorIdBarbeiro(@PathVariable("idBarbeiro") Long idBarbeiro) {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.buscarPorIdBarbeiro(idBarbeiro));
    }

    @GetMapping("/agendamentos/cliente/{idCliente}")
    public ResponseEntity<List<Agendamento>> buscarAgendamentoPorIdCliente(@PathVariable("idCliente") Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.buscarPorIdCliente(idCliente));
    }

    @DeleteMapping("/agendamentos/{id}")
    public ResponseEntity<Void> deletarAgendamentoPorId(@PathVariable("id") Long id) {
        agendamentoService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
