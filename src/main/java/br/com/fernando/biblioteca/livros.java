package br.com.fernando.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fernando.banco.ConexaoMySQL;

import javax.print.DocFlavor;
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
        String sql = "select livros.nome from livros JOIN reservados on livros.id != reservados.ID_livro group by nome";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while(st.next()){
                String nome = st.getString("nome");
                System.out.println(" Nome: " + nome);
            }
            stmt.close();
        } catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public int veSeOLivroJaEstaReservado(String nomeDoLivro){
        String retornaIdDoLivroNaoReservado = "select id from livros where nome = ? and livros.id NOT IN(select ID_livro from reservados)";
        try{
            PreparedStatement stmt = connection.prepareStatement(retornaIdDoLivroNaoReservado);
            stmt.setString(1, nomeDoLivro);
            ResultSet pegaInformacoesDoID = stmt.executeQuery();
            while (pegaInformacoesDoID.next()){
                return pegaInformacoesDoID.getInt("id");
            }
        } catch (SQLException u){
            throw new RuntimeException(u);
        }
        return 0;
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

    public void dataDeEntregaDoLivro(int idAluno){
        String dataDeEntregaDoLivro = "select id_aluno, diaDeEntrega from reservados where ID_aluno = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(dataDeEntregaDoLivro);
            stmt.setString(1, String.valueOf(idAluno));
            ResultSet dataDeEntrega = stmt.executeQuery();
            System.out.println("Dia que você deve entregar seus livros: ");
            while (dataDeEntrega.next()){
                String diaDaEntrega = dataDeEntrega.getString("diaDeEntrega");
                System.out.println(diaDaEntrega);
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public void livrosReservados(){
        String livrosReservados = "select livros.nome from livros JOIN reservados on livros.id = reservados.ID_livro";
        try{
            PreparedStatement stmt = connection.prepareStatement(livrosReservados);
            ResultSet livrosJaReservados = stmt.executeQuery();
            while (livrosJaReservados.next()){
                String nomeDoLivro = livrosJaReservados.getString("nome");
                System.out.println(nomeDoLivro);
            }
        } catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
