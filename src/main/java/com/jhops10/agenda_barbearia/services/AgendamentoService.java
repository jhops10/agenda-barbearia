package com.jhops10.agenda_barbearia.services;

import com.jhops10.agenda_barbearia.dto.AgendamentoRequestDTO;
import com.jhops10.agenda_barbearia.entities.Agendamento;
import com.jhops10.agenda_barbearia.entities.Barbeiro;
import com.jhops10.agenda_barbearia.entities.Cliente;
import com.jhops10.agenda_barbearia.entities.Servico;
import com.jhops10.agenda_barbearia.exceptions.AgendamentoNotFoundException;
import com.jhops10.agenda_barbearia.exceptions.BarbeiroNotFoundException;
import com.jhops10.agenda_barbearia.exceptions.ClienteNotFoundException;
import com.jhops10.agenda_barbearia.repositories.AgendamentoRepository;
import com.jhops10.agenda_barbearia.repositories.BarbeiroRepository;
import com.jhops10.agenda_barbearia.repositories.ClienteRepository;
import com.jhops10.agenda_barbearia.repositories.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final BarbeiroService barbeiroService;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, BarbeiroRepository barbeiroRepository, BarbeiroService barbeiroService, ClienteRepository clienteRepository, ClienteService clienteService, ServicoRepository servicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.barbeiroRepository = barbeiroRepository;
        this.barbeiroService = barbeiroService;
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
        this.servicoRepository = servicoRepository;
    }

    public Agendamento salvar(AgendamentoRequestDTO requestDTO) {
        Barbeiro barbeiro = barbeiroService.buscarPorId(requestDTO.getBarbeiroId());
        Cliente cliente = clienteService.buscarPorId(requestDTO.getClienteId());
        List<Servico> servicos = servicoRepository.findAllById(requestDTO.getServicosIds());

        if (servicos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum Serviço encontrado para os IDs fornecidos.");
        }

        Agendamento agendamento = new Agendamento(null, barbeiro, cliente, servicos, requestDTO.getData());
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

}
