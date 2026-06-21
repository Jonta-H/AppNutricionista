package com.unifacef.app_nutricionista.model;

// Classe abstrata — define o contrato para protocolos de avaliação corporal
public abstract class ProtocoloAvaliacao {

    // Método abstrato — cada subclasse fornecerá sua própria fórmula
    public abstract double calcularDensidadeCorporal(DadosMedidas medidas,
                                                     int idade,
                                                     String genero);

    // Método concreto — equação de Siri, comum a todos os protocolos
    public double converterParaPercentualGordura(double densidadeCorporal) {
        return (495.0 / densidadeCorporal) - 450.0;
    }
}
