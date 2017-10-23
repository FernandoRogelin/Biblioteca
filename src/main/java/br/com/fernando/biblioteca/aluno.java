package br.com.fernando.biblioteca;

import br.com.fernando.banco.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class aluno {

    private Connection connection;

    public aluno() {
        this.connection = ConexaoMySQL.getConexaoMySQL();
    }

    public boolean verContaAluno(String login, int senha){
        String sql = "Select * from aluno";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while(st.next()){
                String logi = st.getString("login");
                int sen = st.getInt("senha");
                if(login.equals(logi) && senha == sen){
                    return true;
                }
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
        return false;
    }
}
