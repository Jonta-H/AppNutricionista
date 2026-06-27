package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planos_alimentares")
public class PlanoAlimentar implements CalculadoraNutricional, Exportavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties({"historicoDietas", "nutricionista", "historicoAvaliacoes", "historicoConsulta", "historicoAnamneses", "dietaVigente"})
    private Paciente paciente;

    @Column(nullable=false)
    private String titulo;
    @Column(nullable=false)
    private LocalDate dataCriacao;
    @Column(nullable=false)
    private String objetivo;
    @Column(nullable=false)
    private Boolean ativo;
    private LocalDate dataValidade;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "plano_alimentar_id")
    private List<Refeicao> refeicoes; // Composição

    public PlanoAlimentar() {
        this.refeicoes = new ArrayList<>();
        this.dataCriacao = LocalDate.now();
        this.ativo = true;
    }

    public PlanoAlimentar(String titulo, String objetivo,
                          LocalDate dataValidade) {
        this.titulo = titulo;
        this.objetivo = objetivo;
        this.dataValidade = dataValidade;
        this.dataCriacao = LocalDate.now();
        this.ativo = true;
        this.refeicoes = new ArrayList<>();
    }

    public Refeicao adicionarRefeicao(String nome, LocalTime horarioSug) {
        Refeicao rf = new Refeicao(nome, horarioSug);
        refeicoes.add(rf);
        return rf; // Retorna para inserção de itens
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularCalorias() {
        double total = 0;
        for (Refeicao r : refeicoes) total += r.calcularCalorias();
        return total;
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularMacronutrientes() {
        double total = 0;
        for (Refeicao r : refeicoes) total += r.calcularMacronutrientes();
        return total;
    }

    // Implementação do método da interface Exportavel
    @Override
    public void anexarExameSemCompressao(File pdf) {
        System.out.println("Exame anexado ao plano: " + pdf.getName());
    }

    // Implementação do método da interface Exportavel
    @Override
    public File exportarComMarcaDagua(PlanoAlimentar plano) {
        System.out.println("Exportando plano " + plano.getTitulo() + " em PDF...");
        return null; // implementação real ficaria a cargo da camada de PDF
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String t) {
        this.titulo = t;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate d) {
        this.dataCriacao = d;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String o) {
        this.objetivo = o;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean a) {
        this.ativo = a;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate d) {
        this.dataValidade = d;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
