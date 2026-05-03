import java.util.ArrayList;
import java.util.List;

// Questionário pré-consulta. Faz parte de uma Consulta
public class QuestionarioPreConsulta {

    private List<String> perguntas;
    private List<String> respostas;
    private String statusPreenchimento; // "PENDENTE" / "PREENCHIDO"
    private Consulta consulta;

    public QuestionarioPreConsulta(Consulta consulta) {
        this.consulta = consulta;
        this.perguntas = new ArrayList<>();
        this.respostas = new ArrayList<>();
        this.statusPreenchimento = "PENDENTE";
    }

    public void adicionarPergunta(String pergunta) {
        perguntas.add(pergunta);
        respostas.add(null);
    }

    public void registrarResposta(int indice, String resposta) {
        if (indice >= 0 && indice < perguntas.size()) {
            respostas.set(indice, resposta);
        }
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public String getStatusPreenchimento() {
        return statusPreenchimento;
    }

    public void setStatusPreenchimento(String s) {
        this.statusPreenchimento = s;
    }
}
