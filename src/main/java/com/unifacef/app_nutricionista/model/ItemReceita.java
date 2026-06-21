package com.unifacef.app_nutricionista.model;

import jakarta.persistence.*;

// Ingrediente de uma receita
@Entity
@Table(name = "itens_receita")
public class ItemReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantidade;
    private String unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;       // Agregação

    public ItemReceita() {
    }

    public ItemReceita(Alimento alimento, double quantidade, String unidadeMedida) {
        this.alimento = alimento;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

    // Implementação dos métodos da interface CalculadoraNutricional
    public double calcularCalorias() {
        if (alimento == null || alimento.getPorcaoReferencia() == 0) return 0;
        double fator = quantidade / alimento.getPorcaoReferencia();
        return alimento.getCalorias() * fator;
    }

    public double calcularMacronutrientes() {
        if (alimento == null || alimento.getPorcaoReferencia() == 0) return 0;
        double fator = quantidade / alimento.getPorcaoReferencia();
        return (alimento.getCarboidratos()
                + alimento.getProteinas()
                + alimento.getGorduras()) * fator;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
