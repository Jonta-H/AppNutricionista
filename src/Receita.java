import java.util.ArrayList;
import java.util.List;

// Receita do banco do nutricionista. Implementa CalculadoraNutricional
public class Receita implements CalculadoraNutricional {

    private String nome;
    private String categoria;
    private String modoPreparo;
    private int rendimento;     // número de porções produzidas
    private List<ItemReceita> ingredientes; // Composição

    public Receita() {
        this.ingredientes = new ArrayList<>();
    }

    public Receita(String nome, String categoria,
                   String modoPreparo, int rendimento) {
        this.nome = nome;
        this.categoria = categoria;
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
        this.ingredientes = new ArrayList<>();
    }

    public void adicionarItem(Alimento alimento, double qntd, String uniMedida) {
        ingredientes.add(new ItemReceita(alimento, qntd, uniMedida));
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularCalorias() {
        double total = 0;
        for (ItemReceita i : ingredientes) total += i.calcularCalorias();
        if (rendimento > 0) return total / rendimento;
        return total;
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularMacronutrientes() {
        double total = 0;
        for (ItemReceita i : ingredientes) total += i.calcularMacronutrientes();
        if (rendimento > 0) return total / rendimento;
        return total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String c) {
        this.categoria = c;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String m) {
        this.modoPreparo = m;
    }

    public int getRendimento() {
        return rendimento;
    }

    public void setRendimento(int r) {
        this.rendimento = r;
    }

    public List<ItemReceita> getIngredientes() {
        return ingredientes;
    }
}
