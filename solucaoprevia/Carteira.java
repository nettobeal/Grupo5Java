package solucaoprevia;

import java.util.ArrayList;
import java.util.List;

public class Carteira {
    private List<Investimento> investimentos = new ArrayList<>();
    private double riscoTotal;
    private double retornoTotal;

    public void adicionarInvestimento(Investimento investimento) {
        investimentos.add(investimento);
        calcularTotais();
    }

    private void calcularTotais() {
        riscoTotal = 0;
        retornoTotal = 0;
        for (Investimento investimento : investimentos) {
            riscoTotal += investimento.getRisco();
            retornoTotal += investimento.getRetornoEsperado();
        }
    }

    public double getRiscoTotal() {
        return riscoTotal;
    }

    public double getRetornoTotal() {
        return retornoTotal;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public boolean atendeAoPerfil(PerfilInvestidor perfilInvestidor) {
        return this.riscoTotal <= perfilInvestidor.getNivelDeRisco();
    }
}
