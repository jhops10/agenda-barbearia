package com.jhops10.agenda_barbearia.services;

import com.jhops10.agenda_barbearia.entities.Cliente;
import com.jhops10.agenda_barbearia.exceptions.ClienteNotFoundException;
import com.jhops10.agenda_barbearia.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com o id " + id + " não encontrado."));
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(id);
        atualizarCampos(cliente, clienteAtualizado);
        return clienteRepository.save(cliente);
    }

    public void deletarPorId(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.deleteById(id);
    }


    private void atualizarCampos(Cliente cliente, Cliente clienteAtualizado) {

        if (clienteAtualizado.getNome() != null && !clienteAtualizado.getNome().isEmpty()) {
            cliente.setNome(clienteAtualizado.getNome());
        }

        if (clienteAtualizado.getEmail() != null && !clienteAtualizado.getEmail().isEmpty()) {
            cliente.setEmail(clienteAtualizado.getEmail());
        }

        if (clienteAtualizado.getTelefone() != null && !clienteAtualizado.getTelefone().isEmpty()) {
            cliente.setTelefone(clienteAtualizado.getTelefone());
        }
    }
}
