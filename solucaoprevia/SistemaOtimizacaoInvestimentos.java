package solucaoprevia;

import java.util.*;

class Investimento {
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

class PerfilInvestidor {
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

class Carteira {
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

class AStar {
    private PerfilInvestidor perfilInvestidor;
    private List<Investimento> listaInvestimentos;

    public AStar(PerfilInvestidor perfilInvestidor, List<Investimento> listaInvestimentos) {
        this.perfilInvestidor = perfilInvestidor;
        this.listaInvestimentos = listaInvestimentos;
    }


    private double heuristica(Carteira carteira) {

        return -(carteira.getRetornoTotal() - carteira.getRiscoTotal());
    }

    public Carteira otimizar() {
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
}

public class SistemaOtimizacaoInvestimentos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<Investimento> investimentos = new ArrayList<>();
        investimentos.add(new Investimento("acao1", 0.08, 0.15));
        investimentos.add(new Investimento("acao2", 0.10, 0.20));
        investimentos.add(new Investimento("acao3", 0.12, 0.25));
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


        AStar aStar = new AStar(perfilSelecionado, investimentos);
        Carteira melhorCarteira = aStar.otimizar();


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
