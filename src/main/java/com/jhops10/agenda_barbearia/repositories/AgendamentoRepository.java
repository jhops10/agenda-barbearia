package com.jhops10.agenda_barbearia.repositories;

import com.jhops10.agenda_barbearia.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByBarbeiroId(Long barbeiroId);
    List<Agendamento> findByClienteId(Long clienteId);

}
