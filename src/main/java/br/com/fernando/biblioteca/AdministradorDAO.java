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
        String dataDeEntregaDoLivro = "select id_aluno, diaDeEntrega from reservados";
        try{
            PreparedStatement stmt = connection.prepareStatement(dataDeEntregaDoLivro);
            ResultSet dataDeEntrega = stmt.executeQuery();
            System.out.println("Dias da entrega dos Livros: ");
            while (dataDeEntrega.next()){
                String diaDaEntrega = dataDeEntrega.getString("diaDeEntrega");
                int idDoLivroReservado = dataDeEntrega.getInt("id_aluno");
                System.out.println("ID do Aluno:" + idDoLivroReservado);
                System.out.println("Data de entrega:" + diaDaEntrega  + "\n");
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
