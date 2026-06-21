package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "Usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Usuario implements Autenticavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    protected String nomeCompleto;
    @Column(nullable = false)
    protected String email;
    @Column(nullable = false)
    protected String senhaHash;
    @Column(nullable = false)
    protected String telefone;
    @Column(nullable = false)
    protected LocalDate dataNascimento;
    @Column(nullable = false)
    protected String genero; // "M", "F" ou "O"
    protected byte[] foto; // opcional

    @JsonCreator
    public Usuario() {
    }

    public Usuario(Long id, String nomeCompleto, String email, String senha,
                   String telefone, LocalDate dataNascimento, String genero) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senhaHash = senha != null ? String.valueOf(senha.hashCode()) : null;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    // Método abstrato — cada subclasse define seu tipo de perfil
    public abstract String getTipoPerfil();

    // Implementação dos métodos da interface Autenticavel
    @Override
    public boolean validarAcesso(String email, String senha) {
        return this.email.equals(email)
                && this.senhaHash != null
                && senha != null
                && this.senhaHash.equals(String.valueOf(senha.hashCode()));
    }

    @Override
    public void alterarSenha(String senhaAtual, String novaSenha) {
        if (this.senhaHash != null && senhaAtual != null && novaSenha != null
                && this.senhaHash.equals(String.valueOf(senhaAtual.hashCode()))) {
            this.senhaHash = String.valueOf(novaSenha.hashCode());
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String n) {
        this.nomeCompleto = n;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String t) {
        this.telefone = t;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate d) {
        this.dataNascimento = d;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String g) {
        this.genero = g;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] f) {
        this.foto = f;
    }
}
