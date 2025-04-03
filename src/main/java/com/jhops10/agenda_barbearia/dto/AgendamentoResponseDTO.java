package com.jhops10.agenda_barbearia.dto;

import com.jhops10.agenda_barbearia.entities.Agendamento;
import com.jhops10.agenda_barbearia.entities.Servico;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoResponseDTO {

    private Long id;
    private String barbeiro;
    private String cliente;
    private List<String> servicos;
    private LocalDateTime data;

    public AgendamentoResponseDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.barbeiro = agendamento.getBarbeiro().getNome();
        this.cliente = agendamento.getCliente().getNome();
        this.servicos = agendamento.getServicos().stream()
                .map(Servico::getServico)
                .toList();
        this.data = agendamento.getData();
    }

    public Long getId() {
        return id;
    }

    public String getBarbeiro() {
        return barbeiro;
    }

    public String getCliente() {
        return cliente;
    }

    public List<String> getServicos() {
        return servicos;
    }

    public LocalDateTime getData() {
        return data;
    }
}
