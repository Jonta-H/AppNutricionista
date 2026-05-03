import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Plano alimentar prescrito a um paciente.
// Implementa duas interfaces: CalculadoraNutricional e Exportavel.
public class PlanoAlimentar implements CalculadoraNutricional, Exportavel {

    private String titulo;
    private LocalDate dataCriacao;
    private String objetivo;
    private boolean ativo;
    private LocalDate dataValidade;
    private List<Refeicao> refeicoes; // Composição

    public PlanoAlimentar() {
        this.refeicoes = new ArrayList<>();
        this.dataCriacao = LocalDate.now();
        this.ativo = true;
    }

    public PlanoAlimentar(String titulo, String objetivo,
                          LocalDate dataValidade) {
        this.titulo = titulo;
        this.objetivo = objetivo;
        this.dataValidade = dataValidade;
        this.dataCriacao = LocalDate.now();
        this.ativo = true;
        this.refeicoes = new ArrayList<>();
    }

    public Refeicao adicionarRefeicao(String nome, LocalTime horarioSug) {
        Refeicao rf = new Refeicao(nome, horarioSug);
        refeicoes.add(rf);
        return rf; // Retorna para inserção de itens
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularCalorias() {
        double total = 0;
        for (Refeicao r : refeicoes) total += r.calcularCalorias();
        return total;
    }

    // Implementação do método da interface CalculadoraNutricional
    @Override
    public double calcularMacronutrientes() {
        double total = 0;
        for (Refeicao r : refeicoes) total += r.calcularMacronutrientes();
        return total;
    }

    // Implementação do método da interface Exportavel
    @Override
    public void anexarExameSemCompressao(File pdf) {
        System.out.println("Exame anexado ao plano: " + pdf.getName());
    }

    // Implementação do método da interface Exportavel
    @Override
    public File exportarComMarcaDagua(PlanoAlimentar plano) {
        System.out.println("Exportando plano " + plano.getTitulo() + " em PDF...");
        return null; // implementação real ficaria a cargo da camada de PDF
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String t) {
        this.titulo = t;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate d) {
        this.dataCriacao = d;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String o) {
        this.objetivo = o;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean a) {
        this.ativo = a;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate d) {
        this.dataValidade = d;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }
}
