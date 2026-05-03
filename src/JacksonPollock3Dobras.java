// Fornece sua própria implementação do método abstrato (anulação)
public class JacksonPollock3Dobras extends ProtocoloAvaliacao {

    // Anulação do método abstrato da superclasse
    @Override
    public double calcularDensidadeCorporal(DadosMedidas medidas,
                                            int idade, String genero) {
        if (genero.equals("M")) {
            // Homens: peitoral + abdominal + coxa
            double soma = medidas.getDobraCutanea("peitoral")
                    + medidas.getDobraCutanea("abdominal")
                    + medidas.getDobraCutanea("coxa");
            return 1.10938
                    - (0.0008267 * soma)
                    + (0.0000016 * soma * soma)
                    - (0.0002574 * idade);
        } else {
            // Mulheres: tríceps + suprailíaca + coxa
            double soma = medidas.getDobraCutanea("triceps")
                    + medidas.getDobraCutanea("suprailiaca")
                    + medidas.getDobraCutanea("coxa");
            return 1.0994921
                    - (0.0009929 * soma)
                    + (0.0000023 * soma * soma)
                    - (0.0001392 * idade);
        }
    }
}
