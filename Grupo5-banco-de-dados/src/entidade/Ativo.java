package entidade;

public class Ativo {
    private int id;
    private String nome;
    private String tipo; // Ex.: "Ação", "ETF", "Título Público"
    private double volatilidade; // Ex.: Desvio-padrão anualizado (0 a 1)
    private double retornoEsperado; // Ex.: Percentual de retorno esperado anual

    public Ativo(int id, String nome, String tipo, double volatilidade, double retornoEsperado) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.volatilidade = volatilidade;
        this.retornoEsperado = retornoEsperado;
    }

    public Ativo(String nome, String tipo, double volatilidade, double retornoEsperado) {
        this.nome = nome;
        this.tipo = tipo;
        this.volatilidade = volatilidade;
        this.retornoEsperado = retornoEsperado;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    // Getters e Setters
    public double getVolatilidade() {
        return volatilidade;
    }

    public void setVolatilidade(double volatilidade) {
        this.volatilidade = volatilidade;
    }

    public double getRetornoEsperado() {
        return retornoEsperado;
    }

    public void setRetornoEsperado(double retornoEsperado) {
        this.retornoEsperado = retornoEsperado;
    }

    @Override
    public String toString() {
        return "Ativo" + " ID= " + id + ", Nome= "+ nome +",Tipo= " + tipo + ", Volatilidade= " + volatilidade + ", retornoEsperado= " + retornoEsperado + '}';
    }
}
