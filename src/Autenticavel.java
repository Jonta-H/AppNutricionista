// Interface para autenticação de login
public interface Autenticavel {

    boolean validarAcesso(String email, String senha);

    void alterarSenha(String senhaAtual, String novaSenha);
}
