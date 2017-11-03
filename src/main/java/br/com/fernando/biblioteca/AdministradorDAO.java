package br.com.fernando.biblioteca;

import br.com.fernando.banco.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdministradorDAO {

    private Connection connection;

    public AdministradorDAO() {
        this.connection = ConexaoMySQL.getConexaoMySQL();
    }

    public void adicionarAdministrador(Usuario usu){
        String sql = "INSERT INTO administrador(login, senha) VALUES (?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usu.getLogin());
            stmt.setString(2, String.valueOf(usu.getSenha()));
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public boolean verContaAdministrador(Usuario usu){
        String sql = "select * from administrador";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while (st.next()){
                String logi = st.getString("Login");
                int sen = st.getInt("Senha");
                if(usu.getLogin().equals(logi) && usu.getSenha() == sen){
                    return true;
                }
            }
        } catch (SQLException u){
            throw new RuntimeException(u);
        }
        return false;
    }

    public void dataDosLivrosReservados(){
        String dataDeEntregaDoLivro = "select livros.nome, diaDeEntrega from reservados JOIN livros ON livros.id = reservados.ID_livro";
        try{
            PreparedStatement stmt = connection.prepareStatement(dataDeEntregaDoLivro);
            ResultSet dataDeEntrega = stmt.executeQuery();
            System.out.println("Dias da entrega dos LivrosDAO: ");
            while (dataDeEntrega.next()){
                String diaDaEntrega = dataDeEntrega.getString("diaDeEntrega");
                String nomeDoLivro = dataDeEntrega.getString("nome");
                System.out.println(nomeDoLivro + " : " + diaDaEntrega);
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public void excluirLivro(int idLivro){
        String sql = "DELETE FROM livros WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(idLivro));
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public boolean verificaLivroParaExcluir(int idLivro){
        String IDLivrosReservados = "select reservados.ID_livro from reservados";
        try{
            PreparedStatement stmt = connection.prepareStatement(IDLivrosReservados);
            ResultSet idLivrosReservados = stmt.executeQuery();
            while (idLivrosReservados.next()){
                int idReservados = idLivrosReservados.getInt("ID_livro");
                if(idLivro == idReservados){
                    return true;
                }
            }
        } catch (SQLException u){
            throw new RuntimeException(u);
        }
        return false;
    }

    public boolean verificaLoginADM(String loginADM){
        String loginDoADM = "select administrador.login from administrador";
        try{
            PreparedStatement stmt = connection.prepareStatement(loginDoADM);
            ResultSet login = stmt.executeQuery();
            while (login.next()){
                String log = login.getString("login");
                if(loginADM.equals(log)){
                    return true;
                }
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
        return false;
    }
}
