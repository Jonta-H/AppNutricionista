package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "anamneses")
public class Anamnese implements GerenciadorHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties({"historicoAnamneses", "nutricionista", "historicoDietas", "historicoConsulta", "historicoAvaliacoes", "dietaVigente"})
    private Paciente paciente;

    @Transient
    private Consulta consulta;
    private String sintomasRelatados;
    private String condutaAdotada;

    public Anamnese() {
    }

    // Implementação do método da interface GerenciadorHistorico.
    @Override
    public String correlacionarAnamneses() {
        return "Anamnese de " + consulta.getPaciente().getNomeCompleto()
                + ": " + sintomasRelatados
                + " | Conduta: " + condutaAdotada;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public String getSintomasRelatados() {
        return sintomasRelatados;
    }

    public void setSintomasRelatados(String s) {
        this.sintomasRelatados = s;
    }

    public String getCondutaAdotada() {
        return condutaAdotada;
    }

    public void setCondutaAdotada(String c) {
        this.condutaAdotada = c;
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
