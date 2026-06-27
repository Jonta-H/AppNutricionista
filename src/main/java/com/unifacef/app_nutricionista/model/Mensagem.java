package com.unifacef.app_nutricionista.model;

import java.time.LocalDateTime;

// Mensagem trocada entre dois Usuarios (remetente e destinatário)
public class Mensagem {

    private Long id;
    private String conteudo;
    private LocalDateTime dataHoraEnvio;
    private boolean lida;
    private Usuario remetente;
    private Usuario destinatario;

    public Mensagem() {
        this.dataHoraEnvio = LocalDateTime.now();
        this.lida = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String c) {
        this.conteudo = c;
    }

    public LocalDateTime getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public void setDataHoraEnvio(LocalDateTime d) {
        this.dataHoraEnvio = d;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean l) {
        this.lida = l;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario u) {
        this.remetente = u;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario u) {
        this.destinatario = u;
    }
}
