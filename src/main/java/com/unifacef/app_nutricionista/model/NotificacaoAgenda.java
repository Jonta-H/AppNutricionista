package com.unifacef.app_nutricionista.model;

// Notificação de agenda (lembrete de consulta)
public class NotificacaoAgenda implements ServicoMensageria {

    private String modeloTexto;
    private String statusEnvio;       // "PENDENTE" / "ENVIADO"
    private Paciente paciente;
    private Consulta consulta;

    public NotificacaoAgenda() {
        this.statusEnvio = "PENDENTE";
    }

    public NotificacaoAgenda(Long id, String modeloTexto,
                             Paciente paciente, Consulta consulta) {
        this.modeloTexto = modeloTexto;
        this.paciente = paciente;
        this.consulta = consulta;
        this.statusEnvio = "PENDENTE";
    }

    // Implementação do método da interface ServicoMensageria
    @Override
    public void notificarStatus(Mensagem msg) {
        System.out.println("Notificando: " + msg.getConteudo());
        this.statusEnvio = "ENVIADO";
    }

    public String getModeloTexto() {
        return modeloTexto;
    }

    public void setModeloTexto(String m) {
        this.modeloTexto = m;
    }

    public String getStatusEnvio() {
        return statusEnvio;
    }

    public void setStatusEnvio(String s) {
        this.statusEnvio = s;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente p) {
        this.paciente = p;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta c) {
        this.consulta = c;
    }
}
