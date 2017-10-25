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
        String sql = "select livros.nome from livros JOIN reservados on livros.id != reservados.ID_livro";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while(st.next()){
                String nome = st.getString("nome");

                System.out.println(" Nome: " + nome + " ");
            }
            stmt.close();
        } catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public int reservaLivros(String nomeDoLivro){
        String idDoLivroParaReserva = "select livros.id from livros where nome = ?";
        String idDoLivroReservado = "select reservados.ID_livro from reservados";
        try{
            PreparedStatement stmt = connection.prepareStatement(idDoLivroParaReserva);
            PreparedStatement stm = connection.prepareStatement(idDoLivroReservado);
            stmt.setString(1, nomeDoLivro);
            stmt.execute();
            ResultSet st = stmt.executeQuery();
            ResultSet idLivroReservado = stm.executeQuery();
            while(st.next()){
                int livroID = st.getInt("id");
                while(idLivroReservado.next()){
                    int idReservado = idLivroReservado.getInt("reservados.ID_livro");
                    if(livroID == idReservado){

                    } else{

                    }
                }
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
        return 0;
    }
}
