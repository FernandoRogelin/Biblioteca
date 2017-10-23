package br.com.fernando.biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       //ConexaoMySQL.getConexaoMySQL();
       //System.out.println(ConexaoMySQL.status);

        Scanner scn = new Scanner(System.in);
        String login;
        int senha;

       //usuario usu = new usuario();
       //usu.setNome("Miauuu");
       //usu.setAno(1997);
       //livros liv = new livros();
       //liv.adicionarLivros(usu);
       //liv.excluirLivro("Miauuuu");
       //liv.verLivrosDisponiveis();

        administrador adm = new administrador();
        aluno alu = new aluno();
        //adm.adicionarAdministrador("Rogelin", 6070);

        System.out.println("Bem-vindo ao software de Biblioteca, Faça seu Login \n");
        System.out.println("Login: ");
        login = scn.next();
        System.out.println("Senha: ");
        senha = scn.nextInt();

        if(adm.verContaAdministrador(login, senha)){
            System.out.println("Entrou na conta como administrador \n");
            System.out.println("1 - Adicionar novo administrador \n2 - Ver livros disponíveis");
            System.out.println("3 - Remover livros \n4 - Adicionar um livro novo");
            System.out.println("5 - Ver datas de livros em atraso \n6 - Livros reservados");
            System.out.println("Escolha a opção: ");
            int opcao = scn.nextInt();
            switch (opcao){
                case 1:

            }
        } else if (alu.verContaAluno(login, senha)){
            System.out.println("Entrou na conta como aluno, o que deseja fazer: \n");
        } else{
            System.out.println("Não tem nenhuma conta, registre-se");
        }

    }
}
