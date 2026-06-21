package com.unifacef.app_nutricionista.model;

import jakarta.persistence.*;

// Item de uma refeição
@Entity
@Table(name = "itens_refeicao")
public class ItemRefeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantidade;
    private String unidadeMedida; // "g", "ml", "un", etc.

    @ManyToOne
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;      // Agregação

    // O item da refeição pode ser tanto um único alimento como uma receita
    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    public ItemRefeicao() {
    }

    public ItemRefeicao(Alimento alimento, double quantidade, String unidadeMedida) {
        this.alimento = alimento;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

    public ItemRefeicao(Receita receita, double quantidade, String unidadeMedida) {
        this.receita = receita;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

    // Métodos da interface CalculadoraNutricional
    public double calcularCalorias() {
        if (this.alimento != null) {
            if (this.alimento.getPorcaoReferencia() <= 0) return 0.0;

            double fator = this.quantidade / this.alimento.getPorcaoReferencia();
            return this.alimento.getCalorias() * fator;
        }

        if (this.receita != null) {
            if (this.receita.getRendimento() <= 0) return 0.0;

            double caloriasTotaisReceita = this.receita.calcularCalorias();
            return (caloriasTotaisReceita / this.receita.getRendimento()) * this.quantidade;
        }
        return 0.0;
    }

    // Métodos da interface CalculadoraNutricional
    public double calcularMacronutrientes() {
        if (this.alimento != null) {
            if (this.alimento.getPorcaoReferencia() <= 0) return 0.0;

            double fator = this.quantidade / this.alimento.getPorcaoReferencia();
            return (this.alimento.getCarboidratos()
                    + this.alimento.getProteinas()
                    + this.alimento.getGorduras()) * fator;
        }

        if (this.receita != null) {
            if (this.receita.getRendimento() <= 0) return 0.0;

            double macrosTotaisReceita = this.receita.calcularMacronutrientes();
            return (macrosTotaisReceita / this.receita.getRendimento()) * this.quantidade;
        }
        return 0.0;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double q) {
        this.quantidade = q;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String u) {
        this.unidadeMedida = u;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento a) {
        this.alimento = a;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
