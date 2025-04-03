package com.jhops10.agenda_barbearia.controllers;

import com.jhops10.agenda_barbearia.dto.AgendamentoRequestDTO;
import com.jhops10.agenda_barbearia.dto.AgendamentoResponseDTO;
import com.jhops10.agenda_barbearia.services.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criarAgendamento(@RequestBody @Valid AgendamentoRequestDTO requestDTO) {
        AgendamentoResponseDTO response = agendamentoService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarTodosAgendamentos() {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarAgendamentoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.buscarPorId(id));
    }

    @GetMapping("/barbeiro/{idBarbeiro}")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarAgendamentoPorIdBarbeiro(@PathVariable("idBarbeiro") Long idBarbeiro) {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.buscarPorIdBarbeiro(idBarbeiro));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarAgendamentoPorIdCliente(@PathVariable("idCliente") Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.buscarPorIdCliente(idCliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamentoPorId(@PathVariable("id") Long id) {
        agendamentoService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
