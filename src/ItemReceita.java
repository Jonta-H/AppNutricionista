// Ingrediente de uma receita
public class ItemReceita {

    private double quantidade;
    private String unidadeMedida;
    private Alimento alimento;       // Agregação

    public ItemReceita() {
    }

    public ItemReceita(Alimento alimento, double quantidade, String unidadeMedida) {
        this.alimento = alimento;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

    // Implementação dos métodos da interface CalculadoraNutricional
    public double calcularCalorias() {
        if (alimento == null || alimento.getPorcaoReferencia() == 0) return 0;
        double fator = quantidade / alimento.getPorcaoReferencia();
        return alimento.getCalorias() * fator;
    }

    public double calcularMacronutrientes() {
        if (alimento == null || alimento.getPorcaoReferencia() == 0) return 0;
        double fator = quantidade / alimento.getPorcaoReferencia();
        return (alimento.getCarboidratos()
                + alimento.getProteinas()
                + alimento.getGorduras()) * fator;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double q) {
        this.quantidade = q;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String u) {
        this.unidadeMedida = u;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento a) {
        this.alimento = a;
    }
}
