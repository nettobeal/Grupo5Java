package solucaoprevia;

import java.util.ArrayList;
import java.util.List;

public class SistemaOtimizacaoInvestimentos {

    public static void main(String[] args) {

        List<Investimento> investimentos = new ArrayList<>();

        investimentos.add(new Investimento("Tesouro Direto", 0.05, 0.03));
        investimentos.add(new Investimento("Ação D", 0.14, 0.30));
        investimentos.add(new Investimento("Bitcoin", 0.20, 0.50));
        investimentos.add(new Investimento("CDB", 0.04, 0.02));


        List<PerfilInvestidor> perfis = new ArrayList<>();
        perfis.add(new PerfilInvestidor("Conservador", 0.2));
        perfis.add(new PerfilInvestidor("Moderado", 0.4));
        perfis.add(new PerfilInvestidor("Avançado", 0.6));


        for (PerfilInvestidor perfil : perfis) {
            System.out.println("\nCarteira otimizada para o perfil " + perfil.getNome() + ":");

            OtimizadorInvestimentos otimizador = new AStar();
            Carteira melhorCarteira = otimizador.otimizar(perfil, investimentos);

            if (melhorCarteira != null) {
                for (Investimento investimento : melhorCarteira.getInvestimentos()) {
                    System.out.println("Investimento: " + investimento.getNome() + ", Retorno Esperado: " + investimento.getRetornoEsperado() * 100 + "%, Risco: " + investimento.getRisco() * 100 + "%");
                }
                System.out.println("Risco Total: " + melhorCarteira.getRiscoTotal() * 100 + "%");
                System.out.println("Retorno Total: " + melhorCarteira.getRetornoTotal() * 100 + "%");
            } else {
                System.out.println("Não foi possível encontrar uma carteira que atenda ao perfil selecionado.");
            }
        }
    }
}
