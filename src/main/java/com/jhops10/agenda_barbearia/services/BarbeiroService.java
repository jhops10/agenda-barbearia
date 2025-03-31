package com.jhops10.agenda_barbearia.services;

import com.jhops10.agenda_barbearia.entities.Barbeiro;
import com.jhops10.agenda_barbearia.exceptions.BarbeiroNotFoundException;
import com.jhops10.agenda_barbearia.repositories.BarbeiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    public BarbeiroService(BarbeiroRepository barbeiroRepository) {
        this.barbeiroRepository = barbeiroRepository;
    }

    public Barbeiro salvar(Barbeiro barbeiro) {
        return barbeiroRepository.save(barbeiro);
    }

    public List<Barbeiro> listarTodos() {
        return barbeiroRepository.findAll();
    }

    public Barbeiro buscarPorId(Long id) {
        return barbeiroRepository.findById(id)
                .orElseThrow(() -> new BarbeiroNotFoundException("Barbeiro com o id " + id + " não encontrado."));
    }

    public Barbeiro atualizar(Long id, Barbeiro barbeiroAtualizado) {
        Barbeiro barbeiro = buscarPorId(id);
        atualizarDados(barbeiro, barbeiroAtualizado);
        return barbeiroRepository.save(barbeiro);

    }

    public void deletarPorId(Long id) {
        Barbeiro barbeiro = buscarPorId(id);
        barbeiroRepository.deleteById(id);
    }

    private void atualizarDados(Barbeiro barbeiro, Barbeiro barbeiroAtualizado) {
        if (barbeiroAtualizado.getNome() != null && !barbeiroAtualizado.getNome().isEmpty()) {
            barbeiro.setNome(barbeiroAtualizado.getNome());
        }

        if (barbeiroAtualizado.getServicos() != null && !barbeiroAtualizado.getServicos().isEmpty()) {
            barbeiro.setServicos(barbeiroAtualizado.getServicos());
        }
    }
}
