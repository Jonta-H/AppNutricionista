package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nutricionistas")
@DiscriminatorValue("2")
public class Nutricionista extends Usuario {

    private String crn;  // CRN
    private String nomeClinica;
    private byte[] logoTipoClinica;

    @OneToMany(mappedBy = "nutricionista", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("nutricionista")
    private List<Paciente> pacientes;

    @Transient
    private List<Receita> bancoReceitas;

    @Transient
    private List<ModeloDieta> modelosDieta;

    @JsonCreator
    public Nutricionista() {
        this.pacientes = new ArrayList<>();
        this.bancoReceitas = new ArrayList<>();
        this.modelosDieta = new ArrayList<>();
    }

    public Nutricionista(Long id, String nome, String email, String senha,
                         String telefone, LocalDate dataNasc, String genero,
                         String crn) {
        // Chama o construtor da superclasse
        super(id, nome, email, senha, telefone, dataNasc, genero);
        this.crn = crn;
        this.pacientes = new ArrayList<>();
        this.bancoReceitas = new ArrayList<>();
        this.modelosDieta = new ArrayList<>();
    }

    // Anulação do método abstrato declarado em Usuario
    @Override
    public String getTipoPerfil() {
        return "Nutricionista";
    }

    public void cadastrarPaciente(Paciente p) {
        pacientes.add(p);
    }

    public PlanoAlimentar criarPlanoAlimentar(Paciente p, String titulo,
                                              String objetivo) {
        PlanoAlimentar plano = new PlanoAlimentar(titulo, objetivo, null);
        p.adicionarDieta(plano);
        return plano; // Retornar para inserção de refeições
    }

    public ModeloDieta salvarModeloDieta(String nome, String descricao) {
        ModeloDieta m = new ModeloDieta(null, nome, descricao);
        modelosDieta.add(m);
        return m; // Retornar para inserção de refeições
    }

    public DocumentoClinico criarDocumento(Long id, String tipo, String marca){
        return new DocumentoClinico(id, tipo, marca);
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Receita> getBancoReceitas() {
        return bancoReceitas;
    }

    public List<ModeloDieta> getModelosDieta() {
        return modelosDieta;
    }

    public byte[] getLogoTipoClinica() {
        return logoTipoClinica;
    }

    public void setLogoTipoClinica(byte[] logoTipoClinica) {
        this.logoTipoClinica = logoTipoClinica;
    }

    public String getNomeClinica() {
        return nomeClinica;
    }

    public void setNomeClinica(String nomeClinica) {
        this.nomeClinica = nomeClinica;
    }
}
