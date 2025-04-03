package com.jhops10.agenda_barbearia.services;

import com.jhops10.agenda_barbearia.dto.AgendamentoRequestDTO;
import com.jhops10.agenda_barbearia.dto.AgendamentoResponseDTO;
import com.jhops10.agenda_barbearia.entities.Agendamento;
import com.jhops10.agenda_barbearia.entities.Barbeiro;
import com.jhops10.agenda_barbearia.entities.Cliente;
import com.jhops10.agenda_barbearia.entities.Servico;
import com.jhops10.agenda_barbearia.exceptions.AgendamentoNotFoundException;
;
import com.jhops10.agenda_barbearia.exceptions.ServicoNotFoundException;
import com.jhops10.agenda_barbearia.repositories.AgendamentoRepository;
import com.jhops10.agenda_barbearia.repositories.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroService barbeiroService;
    private final ClienteService clienteService;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, BarbeiroService barbeiroService, ClienteService clienteService, ServicoRepository servicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.barbeiroService = barbeiroService;
        this.clienteService = clienteService;
        this.servicoRepository = servicoRepository;
    }

    public AgendamentoResponseDTO salvar(AgendamentoRequestDTO requestDTO) {
        Barbeiro barbeiro = barbeiroService.buscarPorId(requestDTO.getBarbeiroId());
        Cliente cliente = clienteService.buscarPorId(requestDTO.getClienteId());
        List<Servico> servicos = servicoRepository.findAllById(requestDTO.getServicosIds());

        if (servicos.isEmpty()) {
            throw new ServicoNotFoundException("Nenhum Serviço encontrado para os IDs fornecidos.");
        }

        Agendamento agendamento = new Agendamento(null, barbeiro, cliente, servicos, requestDTO.getData());
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(agendamentoSalvo);
    }

    public List<AgendamentoResponseDTO> listarTodos() {
        return agendamentoRepository.findAll().stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    public AgendamentoResponseDTO buscarPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoNotFoundException("Agendamento com id " + id + " não encontrado."));

        return new AgendamentoResponseDTO(agendamento);
    }

    public List<AgendamentoResponseDTO> buscarPorIdBarbeiro(Long barbeiroId) {
        return agendamentoRepository.findByBarbeiroId(barbeiroId).stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    public List<AgendamentoResponseDTO> buscarPorIdCliente(Long clienteId) {
        return agendamentoRepository.findByClienteId(clienteId).stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    public void deletarPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoNotFoundException("Agendamento com id " + id + " não encontrado."));
        agendamentoRepository.deleteById(id);
    }

}
