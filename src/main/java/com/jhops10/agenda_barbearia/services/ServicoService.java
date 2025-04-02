package com.jhops10.agenda_barbearia.services;

import com.jhops10.agenda_barbearia.entities.Barbeiro;
import com.jhops10.agenda_barbearia.entities.Servico;
import com.jhops10.agenda_barbearia.exceptions.ServicoNotFoundException;

import com.jhops10.agenda_barbearia.repositories.ServicoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final BarbeiroService barbeiroService;

    public ServicoService(ServicoRepository servicoRepository, BarbeiroService barbeiroService) {
        this.servicoRepository = servicoRepository;
        this.barbeiroService = barbeiroService;
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public List<Servico> buscarServicoPorNome(String nomeServico) {
        return servicoRepository.findByServicoContainingIgnoreCase(nomeServico);
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Serviço com o id " + id + " não encontrado"));

    }

    public Servico atualizar(Long id, Servico servicoAtualizado) {
        Servico servico = buscarPorId(id);
        atualizarDados(servico, servicoAtualizado);
        return servicoRepository.save(servico);
    }

    public void deletar(Long id) {
        Servico servico = buscarPorId(id);
        servicoRepository.deleteById(id);
    }

    public Servico associarBarbeiro(Long idServico, Long idBarbeiro) {
        Servico servico = buscarPorId(idServico);
        Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);

        servico.getBarbeiros().add(barbeiro);
        return servicoRepository.save(servico);
    }

    private void atualizarDados(Servico servico, Servico servicoAtualizado) {

        if (servicoAtualizado.getServico() != null && !servicoAtualizado.getServico().isEmpty()) {
            servico.setServico(servicoAtualizado.getServico());
        }

        if (servicoAtualizado.getDescricaoServico() != null && !servicoAtualizado.getDescricaoServico().isEmpty()) {
            servico.setDescricaoServico(servicoAtualizado.getDescricaoServico());
        }

        if (servicoAtualizado.getPreco() != null) {
            servico.setPreco(servicoAtualizado.getPreco());
        }
    }
}
