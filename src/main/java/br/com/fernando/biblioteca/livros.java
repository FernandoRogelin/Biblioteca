package br.com.fernando.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.fernando.banco.ConexaoMySQL;

public class livros {

    private Connection connection;

    public livros() {
        this.connection = ConexaoMySQL.getConexaoMySQL();
    }

    public void adicionarLivros(usuario usu){
        String sql = "INSERT INTO livros(nome, ano) VALUES(?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usu.getNome());
            stmt.setString(2, String.valueOf(usu.getAno()));
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public void excluirLivro(int ID){
        String sql = "DELETE FROM livros WHERE id = ?";
    }
}
