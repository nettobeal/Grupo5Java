package entidade;

import conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class DAO {

    public List<PerfilRisco> listarPerfisDeRisco() {
        List<PerfilRisco> perfis = new ArrayList<>();
        String sql = "SELECT id_perfil, descricao FROM perfilrisco";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id_perfil = rs.getInt("id_perfil");
                String descricao = rs.getString("descricao");
                perfis.add(new PerfilRisco(id_perfil, descricao));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar perfis de risco: " + e.getMessage());
        }

        return perfis;
    }


    public void cadastroUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, Senha, email, id_Perfil) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setInt(4, (usuario.getPerfilRisco().getId()));

            ps.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar usuário: " + e.getMessage());
        }

    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = """
        SELECT u.idusuario, u.nome, u.senha, u.email, p.id_perfil, p.descricao
        FROM usuario u
        JOIN perfilrisco p ON u.id_perfil = p.id_perfil""";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("idusuario");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String email = rs.getString("email");
                int idPerfil = rs.getInt("id_perfil");
                String descricaoPerfil = rs.getString("descricao");

                PerfilRisco perfil = new PerfilRisco(idPerfil, descricaoPerfil);
                Usuario usuario = new Usuario(id, nome, email, senha, perfil);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage());
        }

        return usuarios;
    }

    public void excluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE idusuario = ?";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário com ID " + id + " excluído com sucesso!");
            } else {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir usuário: " + e.getMessage());
        }

    }

    public void cadastrarPerfis(PerfilRisco perfilRisco){
        String sql = "INSERT INTO perfilrisco (descricao) VALUES (?)";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, perfilRisco.getDescricao());

            ps.executeUpdate();
            System.out.println("Perfil cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar Perfil: " + e.getMessage());
        }
    }
    public void cadastrarAtivo(Ativo ativo) {
        String sql = "INSERT INTO ativos (nome, tipo, volatilidade, retorno_esperado) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ativo.getNome());
            ps.setString(2, ativo.getTipo());
            ps.setDouble(3, ativo.getVolatilidade());
            ps.setDouble(4, ativo.getRetornoEsperado());
            ps.executeUpdate();
            System.out.println("Ativo cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar ativo: " + e.getMessage());
        }
    }

    public List<Ativo> listarAtivos() {
        List<Ativo> ativos = new ArrayList<>();
        String sql = "SELECT * FROM ativos";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                double volatilidade = rs.getDouble("volatilidade");
                double retornoEsperado = rs.getDouble("retorno_esperado");
                ativos.add(new Ativo(id, nome, tipo, volatilidade, retornoEsperado));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar ativos: " + e.getMessage());
        }

        return ativos;
    }
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, senha = ?, email = ?, id_Perfil = ? WHERE idusuario = ?";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setInt(4, usuario.getPerfilRisco().getId());
            ps.setInt(5, usuario.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Usuário com ID " + usuario.getId() + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }


}
