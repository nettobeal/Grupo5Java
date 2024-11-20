package solucaoprevia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaOtimizacaoInvestimentos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Investimento> investimentos = new ArrayList<>();
        investimentos.add(new Investimento("Fundo Imobiliário X", 0.06, 0.10));
        investimentos.add(new Investimento("Fundo Imobiliário Y", 0.07, 0.12));
        investimentos.add(new Investimento("Tesouro Direto", 0.05, 0.03));
        investimentos.add(new Investimento("Ação D", 0.14, 0.30));
        investimentos.add(new Investimento("Bitcoin", 0.20, 0.50));
        investimentos.add(new Investimento("CDB", 0.04, 0.02));

        List<PerfilInvestidor> perfis = new ArrayList<>();
        perfis.add(new PerfilInvestidor("Conservador", 0.2));
        perfis.add(new PerfilInvestidor("Moderado", 0.4));
        perfis.add(new PerfilInvestidor("Agressivo", 0.6));

        System.out.println("Escolha um perfil para otimizar a carteira:");
        for (int i = 0; i < perfis.size(); i++) {
            System.out.println((i + 1) + ". " + perfis.get(i).getNome() + " - Risco Máximo: " + (perfis.get(i).getNivelDeRisco() * 100) + "%");
        }
        System.out.print("Digite o número do perfil escolhido: ");
        int perfilEscolhido = scanner.nextInt();
        PerfilInvestidor perfilSelecionado = perfis.get(perfilEscolhido - 1);

        OtimizadorInvestimentos otimizador = new AStar();
        Carteira melhorCarteira = otimizador.otimizar(perfilSelecionado, investimentos);

        System.out.println("\nCarteira otimizada para " + perfilSelecionado.getNome() + ":");
        if (melhorCarteira != null) {
            for (Investimento investimento : melhorCarteira.getInvestimentos()) {
                System.out.println("Investimento: " + investimento.getNome() + ", Retorno Esperado: " + investimento.getRetornoEsperado() * 100 + "%, Risco: " + investimento.getRisco() * 100 + "%");
            }
            System.out.println("Risco Total: " + melhorCarteira.getRiscoTotal() * 100 + "%");
            System.out.println("Retorno Total: " + melhorCarteira.getRetornoTotal() * 100 + "%");
        } else {
            System.out.println("Não foi possível encontrar uma carteira que atenda ao perfil selecionado.");
        }

        scanner.close();
    }
}

