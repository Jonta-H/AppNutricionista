import java.time.LocalDate;

// Classe abstrata — não pode ser instanciada diretamente
// Implementa a interface Autenticavel
// É herdada por Nutricionista e Paciente
public abstract class Usuario implements Autenticavel {

    protected Long id;
    protected String nomeCompleto;
    protected String email;
    protected String senhaHash;
    protected String telefone;
    protected LocalDate dataNascimento;
    protected String genero;     // "M", "F" ou "O"
    protected byte[] foto; // opcional

    public Usuario() {
    }

    public Usuario(Long id, String nomeCompleto, String email, String senha,
                   String telefone, LocalDate dataNascimento, String genero) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senhaHash = String.valueOf(senha.hashCode()); // hash simples
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
                && this.senhaHash.equals(String.valueOf(senha.hashCode()));
    }

    @Override
    public void alterarSenha(String senhaAtual, String novaSenha) {
        if (this.senhaHash.equals(String.valueOf(senhaAtual.hashCode()))) {
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
