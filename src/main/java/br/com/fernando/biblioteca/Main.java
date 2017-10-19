package br.com.fernando.biblioteca;

public class Main {
    public static void main(String[] args) {
       //ConexaoMySQL.getConexaoMySQL();
       //System.out.println(ConexaoMySQL.status);

       usuario usu = new usuario();
       usu.setNome("Miauuuu");
       usu.setAno(1997);
       livros liv = new livros();
       liv.adicionarLivros(usu);
    }
}
