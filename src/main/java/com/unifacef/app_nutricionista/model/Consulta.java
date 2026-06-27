package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private LocalDateTime dataHora;
    @Column(nullable=false)
    private String status;         // "AGENDADA", "REALIZADA", "CANCELADA"

    @ManyToOne
    @JoinColumn(name = "nutricionista_id", nullable = false)
    @JsonIgnoreProperties("pacientes")
    private Nutricionista nutricionista;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIgnoreProperties({"historicoConsulta", "nutricionista", "historicoDietas", "historicoAvaliacoes", "historicoAnamneses", "dietaVigente"})
    private Paciente paciente;

    @Transient
    private QuestionarioPreConsulta questionario;

    @Transient
    private Anamnese anamnese;

    public Consulta() {
        this.status = "AGENDADA";
    }

    public void realizar(Anamnese anamnese) {
        this.anamnese = anamnese;
        this.status = "REALIZADA";
        if (paciente != null) paciente.adicionarAnamnese(anamnese);
    }

    public QuestionarioPreConsulta criarQuestioniario(){
        QuestionarioPreConsulta quest = new QuestionarioPreConsulta(this);
        setQuestionario(quest);
        return quest;
    }

    public void cancelar() {
        this.status = "CANCELADA";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime d) {
        this.dataHora = d;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String s) {
        this.status = s;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista n) {
        this.nutricionista = n;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente p) {
        this.paciente = p;
    }

    public QuestionarioPreConsulta getQuestionario() {
        return questionario;
    }

    public void setQuestionario(QuestionarioPreConsulta q) {
        this.questionario = q;
    }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese a) {
        this.anamnese = a;
    }
}
