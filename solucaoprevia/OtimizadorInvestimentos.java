package solucaoprevia;

import java.util.List;

interface OtimizadorInvestimentos {
    Carteira otimizar(PerfilInvestidor perfilInvestidor, List<Investimento> listaInvestimentos);
}
