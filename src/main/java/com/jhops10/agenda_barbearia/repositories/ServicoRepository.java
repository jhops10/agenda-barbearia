package com.jhops10.agenda_barbearia.repositories;

import com.jhops10.agenda_barbearia.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    List<Servico> findByServicoContainingIgnoreCase(String servico);
}
