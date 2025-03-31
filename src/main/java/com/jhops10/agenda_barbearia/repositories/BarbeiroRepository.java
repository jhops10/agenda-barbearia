package com.jhops10.agenda_barbearia.repositories;

import com.jhops10.agenda_barbearia.entities.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    List<Barbeiro> findByNomeContainingIgnoreCase(String nome);
}
