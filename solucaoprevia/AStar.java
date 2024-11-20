package solucaoprevia;

import java.util.*;


class AStar implements OtimizadorInvestimentos {

    @Override
    public Carteira otimizar(PerfilInvestidor perfilInvestidor, List<Investimento> listaInvestimentos) {
        PriorityQueue<Carteira> openList = new PriorityQueue<>(Comparator.comparingDouble(this::heuristica));
        Set<Carteira> closedList = new HashSet<>();
        Carteira initialCarteira = new Carteira();
        openList.add(initialCarteira);

        Carteira melhorCarteira = null;

        while (!openList.isEmpty()) {
            Carteira currentCarteira = openList.poll();

            if (currentCarteira.atendeAoPerfil(perfilInvestidor)) {
                if (melhorCarteira == null || currentCarteira.getRetornoTotal() > melhorCarteira.getRetornoTotal()) {
                    melhorCarteira = currentCarteira;
                }
            }

            closedList.add(currentCarteira);

            for (Investimento investimento : listaInvestimentos) {
                if (!currentCarteira.getInvestimentos().contains(investimento)) {
                    Carteira newCarteira = new Carteira();
                    newCarteira.getInvestimentos().addAll(currentCarteira.getInvestimentos());
                    newCarteira.adicionarInvestimento(investimento);

                    if (!closedList.contains(newCarteira) && newCarteira.getRiscoTotal() <= perfilInvestidor.getNivelDeRisco()) {
                        openList.add(newCarteira);
                    }
                }
            }
        }

        return melhorCarteira;
    }

    private double heuristica(Carteira carteira) {
        return -(carteira.getRetornoTotal() - carteira.getRiscoTotal());
    }
}

