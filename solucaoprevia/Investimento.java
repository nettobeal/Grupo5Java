package solucaoprevia;

public class Investimento {
    private String nome;
    private double retornoEsperado;
    private double risco;

    public Investimento(String nome, double retornoEsperado, double risco) {
        this.nome = nome;
        this.retornoEsperado = retornoEsperado;
        this.risco = risco;
    }

    public double getRetornoEsperado() {
        return retornoEsperado;
    }

    public double getRisco() {
        return risco;
    }

    public String getNome() {
        return nome;
    }
}
