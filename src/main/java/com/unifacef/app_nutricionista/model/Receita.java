package com.unifacef.app_nutricionista.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

// Receita do banco do nutricionista. Implementa CalculadoraNutricional
@Entity
@Table(name = "receitas")
public class Receita implements CalculadoraNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da receita é obrigatório")
    @Column(nullable=false)
    private String nome;
    private String categoria;
    private String modoPreparo;
    private int rendimento;     // número de porções produzidas

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receita_id")
    private List<ItemReceita> ingredientes; // Composição

    public Receita() {
        this.ingredientes = new ArrayList<>();
    }

    public void adicionarItem(Alimento alimento, double qntd, String uniMedida) {
        ingredientes.add(new ItemReceita(alimento, qntd, uniMedida));
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularCalorias() {
        double total = 0;
        for (ItemReceita i : ingredientes) total += i.calcularCalorias();
        if (rendimento > 0) return total / rendimento;
        return total;
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularMacronutrientes() {
        double total = 0;
        for (ItemReceita i : ingredientes) total += i.calcularMacronutrientes();
        if (rendimento > 0) return total / rendimento;
        return total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String c) {
        this.categoria = c;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String m) {
        this.modoPreparo = m;
    }

    public int getRendimento() {
        return rendimento;
    }

    public void setRendimento(int r) {
        this.rendimento = r;
    }

    public List<ItemReceita> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<ItemReceita> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
