package br.com.fernando.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fernando.banco.ConexaoMySQL;

public class livros {

    private Connection connection;

    public livros() {
        this.connection = ConexaoMySQL.getConexaoMySQL();
    }

    public void adicionarLivros(String nomeLivroNovo, int anoLivroNovo){
        String sql = "INSERT INTO livros(nome, ano) VALUES(?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nomeLivroNovo);
            stmt.setString(2, String.valueOf(anoLivroNovo));
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public void excluirLivro(String nome){
        String sql = "DELETE FROM livros WHERE nome = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public void verLivrosDisponiveis(){
        String sql = "select * from livros";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while(st.next()){
                String nome = st.getString("nome");
                int ano = st.getInt("ano");

                System.out.println(" Nome: " + nome + " | Ano: " + ano);
            }
            stmt.close();
        } catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
