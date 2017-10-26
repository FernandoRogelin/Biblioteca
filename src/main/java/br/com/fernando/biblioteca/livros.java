package br.com.fernando.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fernando.banco.ConexaoMySQL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public int veSeOLivroJaEstaReservado(String nomeDoLivro){
        String idDoLivroParaReserva = "select livros.id from livros where nome = ?";
        String idDoLivroReservado = "select id from livros where livros.id NOT IN(select ID_livro from reservados)"; //lista de livros disponiveis
        int disponibilidade = 0;
        try{
            PreparedStatement stmt = connection.prepareStatement(idDoLivroParaReserva);
            PreparedStatement stm = connection.prepareStatement(idDoLivroReservado);
            stmt.setString(1, nomeDoLivro);
            stmt.execute();
            ResultSet st = stmt.executeQuery();
            ResultSet idLivroReservado = stm.executeQuery();
            while (st.next()){
                int livroDigitado = st.getInt("id");
                while (idLivroReservado.next()){
                    int livrosReservados = idLivroReservado.getInt("id");
                    if(livroDigitado == livrosReservados){
                        return disponibilidade = livroDigitado;
                    }
                }
            }
            return disponibilidade;
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public int pegarIDdoAluno(String login){
        String pegaIDdoAluno = "select aluno.alunoID from aluno where login = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(pegaIDdoAluno);
            stmt.setString(1, login);
            stmt.execute();
            ResultSet st = stmt.executeQuery();
            while (st.next()){
                int idAluno = st.getInt("alunoID");
                return idAluno;
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
        return 0;
    }

    public void reservaDoLivro(int livroNaoReservado, int login){
        LocalDate hoje = LocalDate.now();
        LocalDate umaSemana = hoje.plusDays(7);
        String insereLivros = "insert into reservados(id_aluno, id_livro, diaDeEntrega) values (?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(insereLivros);
            stmt.setString(1, String.valueOf(login));
            stmt.setString(2, String.valueOf(livroNaoReservado));
            stmt.setString(3, String.valueOf(umaSemana));
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
