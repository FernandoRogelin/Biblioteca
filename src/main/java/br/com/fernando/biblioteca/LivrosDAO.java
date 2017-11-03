package br.com.fernando.biblioteca;

import java.sql.*;

import br.com.fernando.banco.ConexaoMySQL;

import java.time.LocalDate;

public class LivrosDAO {

    private Connection connection;

    public LivrosDAO() {
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

    public void verLivrosDisponiveis(){
        String sql = "select livros.nome from livros where livros.id NOT IN(select ID_livro from reservados)";
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

    public void renovacaoDeLivro(int idAluno, int idLivro){
        String dataDeEntregaPeloNome = "update reservados set diaDeEntrega = ? where ID_aluno = ? and ID_livro = ?";
        LocalDate hoje = LocalDate.now();
        LocalDate umaSemana = hoje.plusDays(7);
        try{
            PreparedStatement stmt = connection.prepareStatement(dataDeEntregaPeloNome);
            stmt.setString(1, String.valueOf(umaSemana));
            stmt.setString(2, String.valueOf(idAluno));
            stmt.setString(3, String.valueOf(idLivro));
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public int idDoLivro(String nomeLivro){
        String idLivroChamado = "select livros.id from livros where nome = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(idLivroChamado);
            stmt.setString(1, nomeLivro);
            ResultSet idLivro = stmt.executeQuery();
            while (idLivro.next()){
                int idLivros = idLivro.getInt("id");
                return idLivros;
            }
        } catch (SQLException u){
            throw new RuntimeException(u);
        }
        return 0;
    }
}
