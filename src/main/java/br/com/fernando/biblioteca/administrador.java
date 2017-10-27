package br.com.fernando.biblioteca;

import br.com.fernando.banco.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class administrador {

    private Connection connection;

    public administrador() {
        this.connection = ConexaoMySQL.getConexaoMySQL();
    }

    public void adicionarAdministrador(String login, int senha){
        String sql = "INSERT INTO administrador(login, senha) VALUES (?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, String.valueOf(senha));
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public boolean verContaAdministrador(String login, int senha){
        String sql = "select * from administrador";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while (st.next()){
                String logi = st.getString("Login");
                int sen = st.getInt("Senha");
                if(login.equals(logi) && senha == sen){
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
            System.out.println("Dias da entrega dos livros: ");
            while (dataDeEntrega.next()){
                String diaDaEntrega = dataDeEntrega.getString("diaDeEntrega");
                int idDoLivroReservado = dataDeEntrega.getInt("id_aluno");
                System.out.println("ID do aluno:" + idDoLivroReservado);
                System.out.println("Data de entrega:" + diaDaEntrega  + "\n");
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
