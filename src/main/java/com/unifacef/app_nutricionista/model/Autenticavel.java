package com.unifacef.app_nutricionista.model;

// Interface para autenticação de login
public interface Autenticavel {

    boolean validarAcesso(String email, String senha);

    void alterarSenha(String senhaAtual, String novaSenha);
}
