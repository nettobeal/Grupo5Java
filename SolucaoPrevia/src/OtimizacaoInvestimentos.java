import java.util.ArrayList;
import java.util.List;

class AtivoFinanceiro {
    private String nome;
    private double risco;
    private double retorno;

    public AtivoFinanceiro(String nome, double risco, double retorno) {
        this.nome = nome;
        this.risco = risco;
        this.retorno = retorno;
    }

    public double getRisco() {
        return risco;
    }

    public double getRetorno() {
        return retorno;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "nome='" + nome + '\'' +
                ", risco=" + risco +
                ", retorno=" + retorno +
                '}';
    }
}

class Carteira {
    private List<AtivoFinanceiro> ativos;
    private double totalRisco;
    private double totalRetorno;

    public Carteira() {
        this.ativos = new ArrayList<>();
        this.totalRisco = 0.0;
        this.totalRetorno = 0.0;
    }

    public void adicionarAtivo(AtivoFinanceiro ativo) {
        this.ativos.add(ativo);
        this.totalRisco += ativo.getRisco();
        this.totalRetorno += ativo.getRetorno();
    }

    public double getTotalRisco() {
        return totalRisco;
    }

    public double getTotalRetorno() {
        return totalRetorno;
    }

    public List<AtivoFinanceiro> getAtivos() {
        return ativos;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "ativos=" + ativos +
                ", totalRisco=" + totalRisco +
                ", totalRetorno=" + totalRetorno +
                '}';
    }
}

public class OtimizacaoInvestimentos {
    public static void main(String[] args) {
        // Definir ativos financeiros (nome, risco, retorno)
        List<AtivoFinanceiro> ativos = new ArrayList<>();
        ativos.add(new AtivoFinanceiro("Ação A", 10, 30));
        ativos.add(new AtivoFinanceiro("Ação B", 15, 40));
        ativos.add(new AtivoFinanceiro("Ação C", 20, 50));
        ativos.add(new AtivoFinanceiro("Ação D", 5, 20));
        ativos.add(new AtivoFinanceiro("Ação E", 8, 25));

        // Definir o limite de risco máximo
        double limiteRisco = 30.0;

        // Chamar a função de otimização
        Carteira melhorCarteira = otimizarCarteira(ativos, limiteRisco);

        // Exibir a melhor carteira encontrada
        System.out.println("Melhor Carteira Encontrada:");
        System.out.println(melhorCarteira);
    }

    public static Carteira otimizarCarteira(List<AtivoFinanceiro> ativos, double limiteRisco) {
        Carteira melhorCarteira = new Carteira();

        // Loop para gerar todas as combinações possíveis de ativos
        int totalCombinacoes = (1 << ativos.size());  // 2^n combinações possíveis
        for (int i = 0; i < totalCombinacoes; i++) {
            Carteira carteiraAtual = new Carteira();

            // Verificar quais ativos estão na carteira atual
            for (int j = 0; j < ativos.size(); j++) {
                if ((i & (1 << j)) != 0) {  // Se o bit j estiver setado
                    carteiraAtual.adicionarAtivo(ativos.get(j));
                }
            }

            // Verificar se a carteira atual está dentro do limite de risco
            if (carteiraAtual.getTotalRisco() <= limiteRisco &&
                    carteiraAtual.getTotalRetorno() > melhorCarteira.getTotalRetorno()) {
                melhorCarteira = carteiraAtual;
            }
        }

        return melhorCarteira;
    }
}
