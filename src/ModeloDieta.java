import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Modelo de dieta reutilizável. Pode ser aplicado a vários pacientes
public class ModeloDieta {

    private Long idDieta;
    private String nome;
    private String descricao;
    private List<Refeicao> refeicoes; // Composição

    public ModeloDieta() {
        this.refeicoes = new ArrayList<>();
    }

    public ModeloDieta(Long id, String nome, String descricao) {
        this.idDieta = id;
        this.nome = nome;
        this.descricao = descricao;
        this.refeicoes = new ArrayList<>();
    }

    public Refeicao adicionarRefeicao(String nome, LocalTime horarioSug) {
        Refeicao rf = new Refeicao(nome, horarioSug);
        refeicoes.add(rf);
        return rf; // Retorna para inserção de itens
    }

    public Long getId() {
        return idDieta;
    }

    public void setId(Long id) {
        this.idDieta = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String d) {
        this.descricao = d;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }
}
