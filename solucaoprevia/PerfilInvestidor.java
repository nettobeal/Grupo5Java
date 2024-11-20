package solucaoprevia;

public class PerfilInvestidor {
    private String nome;
    private double nivelDeRisco;

    public PerfilInvestidor(String nome, double nivelDeRisco) {
        this.nome = nome;
        this.nivelDeRisco = nivelDeRisco;
    }

    public double getNivelDeRisco() {
        return nivelDeRisco;
    }

    public String getNome() {
        return nome;
    }
}


