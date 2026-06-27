package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacientes")
@DiscriminatorValue("paciente")
public class Paciente extends Usuario implements Sincronizavel {

    @Column(nullable = false)
    private String endereco;
    private String observacoesGerais;
    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "nutricionista_id", nullable = false)
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

    @Transient
    private List<Anamnese> historicoAnamneses;

    public Paciente() {
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

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }
}
