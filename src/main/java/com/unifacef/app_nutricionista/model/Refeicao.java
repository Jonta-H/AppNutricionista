package com.unifacef.app_nutricionista.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Refeição. Implementa a interface CalculadoraNutricional
@Entity
@Table(name = "refeicoes")
public class Refeicao implements CalculadoraNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String nome;
    private LocalTime horarioSugerido;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "refeicao_id")
    private List<ItemRefeicao> itens; // Composição

    public Refeicao() {
        this.itens = new ArrayList<>();   // Composição
    }

    public Refeicao(String nome, LocalTime horarioSugerido) {
        this.nome = nome;
        this.horarioSugerido = horarioSugerido;
        this.itens = new ArrayList<>();
    }

    public void adicionarAlimento(Alimento alimento, double qntd, String uniMedida) {
        itens.add(new ItemRefeicao(alimento, qntd, uniMedida));
    }

    public void adicionarReceita(Receita receita, double qntd, String uniMedida) {
        itens.add(new ItemRefeicao(receita, qntd, uniMedida));
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularCalorias() {
        double total = 0;
        for (ItemRefeicao i : itens) total += i.calcularCalorias();
        return total;
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularMacronutrientes() {
        double total = 0;
        for (ItemRefeicao i : itens) total += i.calcularMacronutrientes();
        return total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public LocalTime getHorarioSugerido() {
        return horarioSugerido;
    }

    public void setHorarioSugerido(LocalTime h) {
        this.horarioSugerido = h;
    }

    public List<ItemRefeicao> getItens() {
        return itens;
    }

    public void setItens(List<ItemRefeicao> itens) {
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
