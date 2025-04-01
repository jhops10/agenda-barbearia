package com.jhops10.agenda_barbearia.services;

import com.jhops10.agenda_barbearia.entities.Agendamento;
import com.jhops10.agenda_barbearia.exceptions.AgendamentoNotFoundException;
import com.jhops10.agenda_barbearia.exceptions.BarbeiroNotFoundException;
import com.jhops10.agenda_barbearia.exceptions.ClienteNotFoundException;
import com.jhops10.agenda_barbearia.repositories.AgendamentoRepository;
import com.jhops10.agenda_barbearia.repositories.BarbeiroRepository;
import com.jhops10.agenda_barbearia.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ClienteRepository clienteRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, BarbeiroRepository barbeiroRepository, ClienteRepository clienteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.barbeiroRepository = barbeiroRepository;
        this.clienteRepository = clienteRepository;
    }

    public Agendamento salvar(Agendamento agendamento) {
        verificarBarbeiro(agendamento.getBarbeiro().getId());
        verificaCliente(agendamento.getCliente().getId());
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoNotFoundException("Agendamento com id " + id + " não encontrado."));
    }

    public List<Agendamento> buscarPorIdBarbeiro(Long barbeiroId) {
        return agendamentoRepository.findByBarbeiroId(barbeiroId);
    }

    public List<Agendamento> buscarPorIdCliente(Long clienteId) {
        return agendamentoRepository.findByClienteId(clienteId);
    }

    public void deletarPorId(Long id) {
        Agendamento agendamento = buscarPorId(id);
        agendamentoRepository.deleteById(id);
    }

    private void verificarBarbeiro(Long barbeiroId) {
        barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new BarbeiroNotFoundException("Barbeiro não Encontrado."));
    }

    private void verificaCliente(Long clienteId) {
        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não Encontrado."));
    }
}
