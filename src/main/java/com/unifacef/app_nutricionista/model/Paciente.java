package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacientes")
@DiscriminatorValue("1")
public class Paciente extends Usuario implements Sincronizavel {

    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String observacoesGerais;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "nutricionista_id")
    @JsonIgnoreProperties("pacientes")
    private Nutricionista nutricionista;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("paciente")
    private List<AvaliacaoCorporal> historicoAvaliacoes;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("paciente")
    private List<PlanoAlimentar> historicoDietas;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("paciente")
    private List<Consulta> historicoConsulta;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("paciente")
    private List<Anamnese> historicoAnamneses;

    @JsonCreator
    public Paciente() {
        this.historicoAvaliacoes = new ArrayList<>();
        this.historicoDietas = new ArrayList<>();
        this.historicoConsulta = new ArrayList<>();
        this.historicoAnamneses = new ArrayList<>();
    }

    public Paciente(Long id, String nome, String email, String senha,
                    String telefone, LocalDate dataNasc, String genero,
                    String endereco, String observacoesGerais) {
        super(id, nome, email, senha, telefone, dataNasc, genero);
        this.endereco = endereco;
        this.observacoesGerais = observacoesGerais;
        this.ativo = true;
        this.historicoAvaliacoes = new ArrayList<>();
        this.historicoDietas = new ArrayList<>();
        this.historicoConsulta = new ArrayList<>();
        this.historicoAnamneses = new ArrayList<>();
    }

    // Anulação do método abstrato declarado em Usuario
    @Override
    public String getTipoPerfil() {
        return "Paciente";
    }

    // Implementação do método da interface Sincronizavel
    @Override
    public void disponibilizarDadosOffline() {
        System.out.println("Sincronizando dados do paciente "
                + nomeCompleto + " para uso offline...");
    }

    // Métodos para manipular os históricos
    public void criarAvaliacao(AvaliacaoCorporal a) {
        historicoAvaliacoes.add(a);
    }

    public void adicionarDieta(PlanoAlimentar p) {
        historicoDietas.add(p);
    }

    public void adicionarConsulta(Consulta c) {
        historicoConsulta.add(c);
    }

    public void adicionarAnamnese(Anamnese a) {
        historicoAnamneses.add(a);
    }

    // Retorna a dieta vigente (a primeira marcada como ativa)
    @JsonIgnoreProperties("paciente")
    public PlanoAlimentar getDietaVigente() {
        for (PlanoAlimentar p : historicoDietas) {
            if (p.isAtivo()) return p;
        }
        return null;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String e) {
        this.endereco = e;
    }

    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public void setObservacoesGerais(String o) {
        this.observacoesGerais = o;
    }

    public List<AvaliacaoCorporal> getHistoricoAvaliacoes() {
        return historicoAvaliacoes;
    }

    public List<PlanoAlimentar> getHistoricoDietas() {
        return historicoDietas;
    }

    public List<Consulta> getHistoricoConsulta() {
        return historicoConsulta;
    }

    public List<Anamnese> getHistoricoAnamneses() {
        return historicoAnamneses;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }
}
