// Anamnese da consulta. Implementa a interface GerenciadorHistorico
public class Anamnese implements GerenciadorHistorico {

    private Consulta consulta;
    private String sintomasRelatados;
    private String condutaAdotada;

    public Anamnese() {
    }

    public Anamnese(Consulta consulta,
                    String sintomasRelatados, String condutaAdotada) {
        this.consulta = consulta;
        this.sintomasRelatados = sintomasRelatados;
        this.condutaAdotada = condutaAdotada;
    }

    // Implementação do método da interface GerenciadorHistorico.
    @Override
    public String correlacionarAnamneses() {
        return "Anamnese de " + consulta.getPaciente().getNomeCompleto()
                + ": " + sintomasRelatados
                + " | Conduta: " + condutaAdotada;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public String getSintomasRelatados() {
        return sintomasRelatados;
    }

    public void setSintomasRelatados(String s) {
        this.sintomasRelatados = s;
    }

    public String getCondutaAdotada() {
        return condutaAdotada;
    }

    public void setCondutaAdotada(String c) {
        this.condutaAdotada = c;
    }
}
