package com.unifacef.app_nutricionista.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nutricionista")
@DiscriminatorValue("nutricionista")
public class Nutricionista extends Usuario {

    @NotBlank(message = "CRN é obrigatório")
    @Column(nullable=false)
    private String crn;  // CRN
    @NotBlank(message = "Nome da clínica é obrigatório")
    @Column(nullable=false)
    private String nomeClinica;
    @Column(name = "url_logoClinica", length = 521)
    private String logoTipoClinica;

    @OneToMany(mappedBy = "nutricionista", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("nutricionista")
    private List<Paciente> pacientes;

    @Transient
    private List<Receita> bancoReceitas;

    @Transient
    private List<ModeloDieta> modelosDieta;

    public Nutricionista() {
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

    public String getLogoTipoClinica() {
        return logoTipoClinica;
    }

    public void setLogoTipoClinica(String logoTipoClinica) {
        this.logoTipoClinica = logoTipoClinica;
    }

    public String getNomeClinica() {
        return nomeClinica;
    }

    public void setNomeClinica(String nomeClinica) {
        this.nomeClinica = nomeClinica;
    }
}
