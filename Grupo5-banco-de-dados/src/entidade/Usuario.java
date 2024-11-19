package entidade;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private PerfilRisco perfilRisco;

    // Construtor para consulta (com todos os campos)
    public Usuario(int id, String nome, String email, String senha, PerfilRisco perfilRisco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfilRisco = perfilRisco;
    }

    // Construtor para cadastro (sem ID)
    public Usuario(String nome, String email, String senha, PerfilRisco perfilRisco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfilRisco = perfilRisco;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilRisco getPerfilRisco() {
        return perfilRisco;
    }

    public void setPerfilRisco(PerfilRisco perfilRisco) {
        this.perfilRisco = perfilRisco;
    }

}
