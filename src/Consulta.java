import java.time.LocalDateTime;

/**
 * A Consulta materializa a associação entre Nutricionista e Paciente. Sempre
 * que existe uma consulta, há um nutricionista e um paciente envolvidos
 */
public class Consulta {

    private Long id;
    private LocalDateTime dataHora;
    private String status;         // "AGENDADA", "REALIZADA", "CANCELADA"
    private Nutricionista nutricionista;
    private Paciente paciente;
    private QuestionarioPreConsulta questionario;
    private Anamnese anamnese;

    public Consulta() {
        this.status = "AGENDADA";
    }

    public Consulta(Long id, LocalDateTime dataHora,
                    Nutricionista nutricionista, Paciente paciente) {
        this.id = id;
        this.dataHora = dataHora;
        this.nutricionista = nutricionista;
        this.paciente = paciente;
        this.status = "AGENDADA";
    }

    public void realizar(Anamnese anamnese) {
        this.anamnese = anamnese;
        this.status = "REALIZADA";
        if (paciente != null) paciente.adicionarAnamnese(anamnese);
    }

    public QuestionarioPreConsulta criarQuestioniario(){
        QuestionarioPreConsulta quest = new QuestionarioPreConsulta(this);
        setQuestionario(quest);
        return quest;
    }

    public void cancelar() {
        this.status = "CANCELADA";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime d) {
        this.dataHora = d;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String s) {
        this.status = s;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista n) {
        this.nutricionista = n;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente p) {
        this.paciente = p;
    }

    public QuestionarioPreConsulta getQuestionario() {
        return questionario;
    }

    public void setQuestionario(QuestionarioPreConsulta q) {
        this.questionario = q;
    }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese a) {
        this.anamnese = a;
    }
}
