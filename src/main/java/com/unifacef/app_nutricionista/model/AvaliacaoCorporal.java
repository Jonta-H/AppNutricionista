package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

/**
 * Avaliação corporal de um paciente em uma data específica.
 * Usa um ProtocoloAvaliacao (que pode ser JacksonPollock3Dobras ou JacksonPollock7Dobras).
 */
@Entity
public class AvaliacaoCorporal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate dataAvaliacao;
    private double peso;
    private double altura;
    private double imc;
    private double massaMagra;
    private double massaGorda;
    private int idadePacienteNaData;


    @Embedded
    private DadosMedidas medidas;
    @Transient
    private ProtocoloAvaliacao protocolo; // Referência aos protocolos de avaliação
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties({"historicoAvaliacoes", "nutricionista", "historicoDietas", "historicoConsulta", "historicoAnamneses", "dietaVigente"})
    private Paciente paciente; // Referência ao paciente

    public AvaliacaoCorporal() {
        this.medidas = new DadosMedidas();
    }

    public AvaliacaoCorporal(Long id, LocalDate dataAvaliacao,
                             double peso, double altura, Paciente paciente) {
        this.id = id;
        this.dataAvaliacao = dataAvaliacao;
        this.peso = peso;
        this.altura = altura;
        this.paciente = paciente;
        this.medidas = new DadosMedidas();
        calcularIMC();
    }

    public double calcularIMC() {
        if (altura > 0) {
            this.imc = peso / (altura * altura);
        }
        return imc;
    }

    public double executarCalculoGordura() {
        // Verifica se objetos não estão vazios antes de calcular
        if (this.protocolo == null || this.paciente == null || this.medidas == null) {
            return 0.0;
        }
        Map<String, Double> dobras = this.medidas.getDobrasCutaneas();

        // Executa o cálculo com a fórmula necessária
        double densidade = this.protocolo.calcularDensidadeCorporal(
                medidas,
                this.idadePacienteNaData,
                this.paciente.getGenero()
        );

        double percentualGordura = this.protocolo.converterParaPercentualGordura(densidade);

        this.massaGorda = this.peso * (percentualGordura / 100.0);
        this.massaMagra = this.peso - this.massaGorda;

        return percentualGordura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate d) {
        this.dataAvaliacao = d;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double p) {
        this.peso = p;
        calcularIMC();
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double a) {
        this.altura = a;
        calcularIMC();
    }

    public double getImc() {
        return imc;
    }

    public double getMassaMagra() {
        return massaMagra;
    }

    public double getMassaGorda() {
        return massaGorda;
    }

    public int getIdadePacienteNaData() {
        return idadePacienteNaData;
    }

    public DadosMedidas getMedidas() {
        return medidas;
    }

    public ProtocoloAvaliacao getProtocolo() {
        return protocolo;
    }

    public void setIdadePacienteNaData(int idade) {
        this.idadePacienteNaData = idade;
    }

    public void setIdadePacienteNaData() {
        if (paciente != null && paciente.getDataNascimento() != null) {
            this.idadePacienteNaData = Period.between(
                    paciente.getDataNascimento(), LocalDate.now()).getYears();
        }
    }

    public void setMedidas(DadosMedidas medidas) {
        this.medidas = medidas;
    }

    public void calcularIndices() {
        calcularIMC();
    }

    public void setProtocolo(ProtocoloAvaliacao p) {
        this.protocolo = p;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente p) {
        this.paciente = p;
    }
}
