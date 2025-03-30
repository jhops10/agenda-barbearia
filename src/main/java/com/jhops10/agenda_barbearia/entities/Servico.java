package com.jhops10.agenda_barbearia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String servico;

    @NotBlank
    @Column(nullable = false)
    private String descricaoServico;

    @NotNull
    @Column(nullable = false)
    private BigDecimal preco;

    @ManyToMany(mappedBy = "servicos")
    private List<Barbeiro> barbeiros;

    public Servico() {
    }

    public Servico(Long id, String servico, String descricaoServico, BigDecimal preco, List<Barbeiro> barbeiros) {
        this.id = id;
        this.servico = servico;
        this.descricaoServico = descricaoServico;
        this.preco = preco;
        this.barbeiros = barbeiros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<Barbeiro> getBarbeiros() {
        return barbeiros;
    }

    public void setBarbeiros(List<Barbeiro> barbeiros) {
        this.barbeiros = barbeiros;
    }
}
