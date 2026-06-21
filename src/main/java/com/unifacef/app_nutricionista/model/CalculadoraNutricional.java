package com.unifacef.app_nutricionista.model;

// Classe interface implementada por PlanoAlimentar, Refeicao e Receita
public interface CalculadoraNutricional {

    double calcularCalorias();

    double calcularMacronutrientes();
}
