package br.com.fernando.biblioteca;

import br.com.fernando.banco.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO() {
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

    public int pegarIDdoAluno(String login){
        String pegaIDdoAluno = "select aluno.alunoID from aluno where login = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(pegaIDdoAluno);
            stmt.setString(1, login);
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

    public void dataDeEntregaDoLivro(int idAluno){
        String dataDeEntregaDoLivro = "select livros.nome, diaDeEntrega from reservados JOIN livros ON livros.id = reservados.ID_livro where reservados.ID_aluno = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(dataDeEntregaDoLivro);
            stmt.setString(1, String.valueOf(idAluno));
            ResultSet dataDeEntrega = stmt.executeQuery();
            System.out.println("Nome e Dia do livro que você deve entregar: ");
            while (dataDeEntrega.next()){
                String nomeDoLivro = dataDeEntrega.getString("nome");
                String diaDaEntrega = dataDeEntrega.getString("diaDeEntrega");
                System.out.println(nomeDoLivro + " : " + diaDaEntrega);
            }
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
}
