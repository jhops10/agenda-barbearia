package com.jhops10.agenda_barbearia.repositories;

import com.jhops10.agenda_barbearia.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
