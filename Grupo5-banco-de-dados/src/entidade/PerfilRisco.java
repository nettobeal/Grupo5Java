package entidade;

public class PerfilRisco {
    private int id_perfil;
    private String descricao;

    public PerfilRisco(String descricao) {
        this.descricao = descricao;
    }

    // Construtor
    public PerfilRisco(int id, String descricao) {
        this.id_perfil = id;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id_perfil;
    }

    public void setId(int id) {
        this.id_perfil = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
