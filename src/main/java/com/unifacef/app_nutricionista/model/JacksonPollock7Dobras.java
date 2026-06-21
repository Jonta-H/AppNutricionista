package com.unifacef.app_nutricionista.model;

// Outra implementação do mesmo método — exemplo de polimorfismo
public class JacksonPollock7Dobras extends ProtocoloAvaliacao {

    // Anulação (override) do método abstrato da superclasse
    @Override
    public double calcularDensidadeCorporal(DadosMedidas medidas,
                                            int idade, String genero) {
        // Soma das 7 dobras (mesmas para homens e mulheres)
        double soma = medidas.getDobraCutanea("peitoral")
                + medidas.getDobraCutanea("axilarMedia")
                + medidas.getDobraCutanea("triceps")
                + medidas.getDobraCutanea("subescapular")
                + medidas.getDobraCutanea("abdominal")
                + medidas.getDobraCutanea("suprailiaca")
                + medidas.getDobraCutanea("coxa");

        if (genero.equals("M")) {
            return 1.112
                    - (0.00043499 * soma)
                    + (0.00000055 * soma * soma)
                    - (0.00028826 * idade);
        } else {
            return 1.097
                    - (0.00046971 * soma)
                    + (0.00000056 * soma * soma)
                    - (0.00012828 * idade);
        }
    }
}
