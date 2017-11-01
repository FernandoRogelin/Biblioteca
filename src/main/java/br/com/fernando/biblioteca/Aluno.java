package br.com.fernando.biblioteca;

import br.com.fernando.banco.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno {

    private Connection connection;

    public Aluno() {
        this.connection = ConexaoMySQL.getConexaoMySQL();
    }

    public boolean verContaAluno(Usuario usu){
        String sql = "Select * from aluno";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while(st.next()){
                String logi = st.getString("login");
                int sen = st.getInt("senha");
                if(usu.getLogin().equals(logi) && usu.getSenha() == sen){
                    return true;
                }
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
        return false;
    }

    public void cadastroDeNovoAluno(Usuario usu){
        String sql = "INSERT INTO aluno (login, senha, nome, email) values(?, ?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usu.getLogin());
            stmt.setString(2, String.valueOf(usu.getSenha()));
            stmt.setString(3, usu.getNome());
            stmt.setString(4, usu.getEmail());
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
