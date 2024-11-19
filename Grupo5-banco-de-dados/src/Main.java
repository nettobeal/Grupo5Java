import entidade.DAO;
import entidade.PerfilRisco;
import entidade.Usuario;
import entidade.Ativo;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DAO dao = new DAO();
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> cadastrarPerfilDeRisco(scanner, dao);
                case 2 -> cadastrarUsuario(scanner, dao);
                case 3 -> atualizarUsuario(scanner, dao);
                case 4 -> listarUsuarios(dao);
                case 5 -> excluirUsuario(scanner, dao);
                case 6 -> cadastrarAtivo(scanner,dao);
                case 7 -> ListarAtivos(scanner, dao);
                case 8 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== Menu de Operações =====");
        System.out.println("1. Cadastrar Perfis de Risco");
        System.out.println("2. Cadastrar novo usuário");
        System.out.println("3. Atualizar usuário");
        System.out.println("4. Listar usuários");
        System.out.println("5. Exlcuir usuários");
        System.out.println("6. Cadastrar Ativos");
        System.out.println("7. Listar Ativos");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarPerfilDeRisco(Scanner scanner, DAO dao) {
        System.out.println("\n===== Cadastro de Perfil de Risco =====");
        System.out.print("Digite o novo Perfil de Risco: ");
        String descricao = scanner.nextLine();

        PerfilRisco perfilRisco = new PerfilRisco(0, descricao);
        try {
            dao.cadastrarPerfis(perfilRisco);
            System.out.println("Perfil cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar perfil de risco: " + e.getMessage());
        }
    }

    private static void cadastrarUsuario(Scanner scanner, DAO dao) {
        System.out.println("\n===== Cadastro de Usuário =====");
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        // Listar perfis de risco disponíveis
        System.out.println("\nEscolha o perfil de risco:");
        List<PerfilRisco> perfis = dao.listarPerfisDeRisco();
        for (PerfilRisco perfil : perfis) {
            System.out.println(perfil.getId() + ". " + perfil.getDescricao());
        }

        System.out.print("Digite o ID do perfil de risco: ");
        int perfilRiscoId = scanner.nextInt();
        scanner.nextLine();

        // Buscar o perfil selecionado
        PerfilRisco perfilSelecionado = perfis.stream()
                .filter(perfil -> perfil.getId() == perfilRiscoId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Perfil de risco inválido"));

        // Criar e cadastrar o usuário
        Usuario usuario = new Usuario(nome, email, senha, perfilSelecionado);
        try {
            dao.cadastroUsuario(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
    private static void atualizarUsuario(Scanner scanner, DAO dao) {
        System.out.println("\n===== Atualizar Usuário =====");

        // Solicitar ID do usuário a ser atualizado
        System.out.print("Digite o ID do usuário a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        // Solicitar os novos dados do usuário
        System.out.print("Digite o novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o novo email: ");
        String email = scanner.nextLine();

        System.out.print("Digite a nova senha: ");
        String senha = scanner.nextLine();

        // Listar perfis de risco disponíveis
        System.out.println("\nEscolha o novo perfil de risco:");
        List<PerfilRisco> perfis = dao.listarPerfisDeRisco();
        for (PerfilRisco perfil : perfis) {
            System.out.println(perfil.getId() + ". " + perfil.getDescricao());
        }

        System.out.print("Digite o ID do novo perfil de risco: ");
        int perfilRiscoId = scanner.nextInt();
        scanner.nextLine();

        // Buscar o perfil selecionado
        PerfilRisco perfilSelecionado = perfis.stream()
                .filter(perfil -> perfil.getId() == perfilRiscoId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Perfil de risco inválido"));

        // Criar um objeto `Usuario` com os novos dados
        Usuario usuario = new Usuario(id, nome, email, senha, perfilSelecionado);

        // Atualizar o usuário no banco de dados
        try {
            dao.atualizarUsuario(usuario);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }


    private static void listarUsuarios(DAO dao) {
        System.out.println("\n===== Lista de Usuários =====");
        try {
            List<Usuario> usuarios = dao.listarUsuarios();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
            } else {
                for (Usuario u : usuarios) {
                    System.out.println("ID: " + u.getId());
                    System.out.println("Nome: " + u.getNome());
                    System.out.println("Email: " + u.getEmail());
                    System.out.println("Senha: " + u.getSenha());
                    System.out.println("Perfil: " + u.getPerfilRisco().getDescricao());
                    System.out.println("-----------------------------");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    private static void excluirUsuario(Scanner scanner, DAO dao) {
        System.out.println("\n===== Exclusão de Usuário =====");
        System.out.print("Digite o ID do usuário a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            dao.excluirUsuario(id);
        } catch (Exception e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
    private static void cadastrarAtivo(Scanner scanner, DAO dao){
        System.out.println("\n===== Cadastro de Ativo =====");
        System.out.print("Nome do ativo: ");
        String nomeAtivo = scanner.nextLine();

        System.out.print("Tipo do ativo (ex.: Ação, ETF, Título Público): ");
        String tipo = scanner.nextLine();

        System.out.print("Risco (0 a 1): ");
        double risco = scanner.nextDouble() / 100;


        System.out.print("Retorno esperado (%): ");
        double retornoEsperado = scanner.nextDouble() / 100;

        Ativo ativo = new Ativo(nomeAtivo, tipo, risco, retornoEsperado);
        dao.cadastrarAtivo(ativo);
    }
    private static void ListarAtivos(Scanner scanner, DAO dao) {
        System.out.println("\n===== Lista de Ativos =====");
        try {
            List<Ativo> ativos = dao.listarAtivos();
            if (ativos.isEmpty()) {
                System.out.println("Nenhum ativo cadastrado.");
            } else {
                for (Ativo a : ativos) {
                    System.out.println(a); // Chama o toString() de Ativo
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar ativos: " + e.getMessage());
        }
    }

}
