package com.unifacef.app_nutricionista.model;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

// Encapsula as medições brutas do paciente para organizar a classe AvaliacaoCorporal.
@Embeddable
public class DadosMedidas {

    // Combinação chave-valor para parte do corpo e medida, respectivamente
    @ElementCollection
    @CollectionTable(name = "medidas_circunferencias", joinColumns = @JoinColumn(name = "avaliacao_id"))
    @MapKeyColumn(name = "local_corpo")
    @Column(name = "valor")
    private Map<String, Double> circunferencias;

    @ElementCollection
    @CollectionTable(name = "medidas_dobras", joinColumns = @JoinColumn(name = "avaliacao_id"))
    @MapKeyColumn(name = "local_corpo")
    @Column(name = "valor")
    private Map<String, Double> dobrasCutaneas;

    public DadosMedidas() {
        this.circunferencias = new HashMap<>();
        this.dobrasCutaneas = new HashMap<>();
    }

    // O método 'put' do Map adiciona um novo valor ou atualiza se a chave já existir
    public void adicionarCircunferencia(String local, double valor) {
        if (local != null && !local.trim().isEmpty()) {
            this.circunferencias.put(local.toLowerCase(), valor);
        }
    }

    public void adicionarDobraCutanea(String local, double valor) {
        if (local != null && !local.trim().isEmpty()) {
            this.dobrasCutaneas.put(local.toLowerCase(), valor);
        }
    }

    // Retorna a medida de um local específico. Se não existir, retorna 0.0 para evitar NullPointerException
    public double getDobraCutanea(String local) {
        return this.dobrasCutaneas.getOrDefault(local.toLowerCase(), 0.0);
    }

    public double getCircunferencia(String local) {
        return this.circunferencias.getOrDefault(local.toLowerCase(), 0.0);
    }

    public Map<String, Double> getCircunferencias() {
        return circunferencias;
    }

    public Map<String, Double> getDobrasCutaneas() {
        return dobrasCutaneas;
    }
}