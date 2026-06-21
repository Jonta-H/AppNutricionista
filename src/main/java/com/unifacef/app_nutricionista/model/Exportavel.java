package com.unifacef.app_nutricionista.model;

import java.io.File;

// Classe interface implementada por PlanoAlimentar e DocumentoClinico.
public interface Exportavel {

    void anexarExameSemCompressao(File pdf);

    File exportarComMarcaDagua(PlanoAlimentar plano);
}
