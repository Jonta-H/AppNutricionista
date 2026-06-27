package com.unifacef.app_nutricionista.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alimentos")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nome;
    private double calorias;
    private double carboidratos;
    private double proteinas;
    private double gorduras;
    private double fibras;
    private double porcaoReferencia;

    public Alimento() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double c) {
        this.calorias = c;
    }

    public double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(double v) {
        this.carboidratos = v;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double v) {
        this.proteinas = v;
    }

    public double getGorduras() {
        return gorduras;
    }

    public void setGorduras(double v) {
        this.gorduras = v;
    }

    public double getFibras() {
        return fibras;
    }

    public void setFibras(double v) {
        this.fibras = v;
    }

    public double getPorcaoReferencia() {
        return porcaoReferencia;
    }

    public void setPorcaoReferencia(double v) {
        this.porcaoReferencia = v;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
