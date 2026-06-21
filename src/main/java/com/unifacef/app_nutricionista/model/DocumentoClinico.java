package com.unifacef.app_nutricionista.model;

import java.io.File;

// Documento clínico exportável (PDF).
public class DocumentoClinico implements Exportavel {

    private Long id;
    private String tipoDocumento;
    private byte[] arquivoPDF;
    private String marcaDagua;

    public DocumentoClinico() {
    }

    public DocumentoClinico(Long id, String tipoDocumento, String marcaDagua) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.marcaDagua = marcaDagua;
    }

    // Implementação dos métodos da interface Exportavel.
    @Override
    public void anexarExameSemCompressao(File pdf) {
        System.out.println("Anexando exame ao documento: " + pdf.getName());
    }

    @Override
    public File exportarComMarcaDagua(PlanoAlimentar plano) {
        System.out.println("Exportando " + tipoDocumento
                + " com marca d'água: " + marcaDagua);
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String t) {
        this.tipoDocumento = t;
    }

    public byte[] getArquivoPDF() {
        return arquivoPDF;
    }

    public void setArquivoPDF(byte[] a) {
        this.arquivoPDF = a;
    }

    public String getMarcaDagua() {
        return marcaDagua;
    }

    public void setMarcaDagua(String m) {
        this.marcaDagua = m;
    }
}
