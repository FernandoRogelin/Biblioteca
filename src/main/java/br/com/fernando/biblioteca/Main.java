package br.com.fernando.biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String login;
        int senha;

        livros liv = new livros();
        administrador adm = new administrador();
        aluno alu = new aluno();

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
            System.out.println("7 - Sair");
            System.out.println("Escolha a opção: ");
            int opcao = scn.nextInt();
            switch (opcao){
                case 1:
                    String loginADM;
                    int senhaADM;
                    System.out.println("Login do novo administrador:");
                    loginADM = scn.next();
                    System.out.println("Senha do novo administrador: ");
                    senhaADM = scn.nextInt();
                    adm.adicionarAdministrador(loginADM, senhaADM);
                    break;
                case 2:
                    liv.verLivrosDisponiveis();
                    break;
                case 3:
                    String nomeLivroExcluido;
                    System.out.println("Qual o nome do livro que deseja excluir??");
                    nomeLivroExcluido = scn.next();
                    liv.excluirLivro(nomeLivroExcluido);
                    break;
                case 4:
                    String nomeLivroNovo;
                    int anoDoLivroNovo;
                    System.out.println("Nome do novo livro: ");
                    nomeLivroNovo = scn.next();
                    System.out.println("Ano do livro: ");
                    anoDoLivroNovo = scn.nextInt();
                    liv.adicionarLivros(nomeLivroNovo, anoDoLivroNovo);
            }
        } else if (alu.verContaAluno(login, senha)){
            System.out.println("Entrou na conta como aluno, o que deseja fazer: \n");
            System.out.println("1 - Livros disponíveis\n2 - Reservado");
            System.out.println("3 - Data de entrega\n4 - Renovação");
            int option = scn.nextInt();
            switch (option){
                case 1:
                    liv.verLivrosDisponiveis();
                case 2:
                    String reservado;
                    System.out.println("Nome do livro que deseja reservar: ");
                    reservado = scn.next();
                    liv.reservaLivros(reservado);
            }
        } else{
            System.out.println("Não tem nenhuma conta, registre-se");
            String nomeNovoAluno, emailNovoAluno, loginNovoAluno;
            int senhaNovoAluno;
            System.out.println("Login: ");
            loginNovoAluno = scn.next();
            System.out.println("Senha: ");
            senhaNovoAluno = scn.nextInt();
            System.out.println("Nome: ");
            nomeNovoAluno = scn.next();
            System.out.println("E-mail");
            emailNovoAluno = scn.next();
            alu.cadastroDeNovoAluno(loginNovoAluno, senhaNovoAluno, nomeNovoAluno, emailNovoAluno);
        }

    }
}
