package br.com.fernando.biblioteca;

import br.com.fernando.controller.Controller;
import br.com.fernando.controller.ControllerUsuario;

import java.util.Scanner;

public class Main {

    public Main(){

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String login;
        int senha;
        ControllerUsuario cont = new ControllerUsuario();

        System.out.println("Bem-vindo ao software de Biblioteca, Faça seu Login \n");
        System.out.println("Login: ");
        login = scn.next();
        System.out.println("Senha: ");
        senha = scn.nextInt();
        cont.verificaConta(login, senha);
    }

    public void menuAdministrador(Usuario usu){
        Scanner scn = new Scanner(System.in);
        Controller cont = new Controller();
        System.out.println("Entrou na conta como Administrador \n");
        System.out.println("1 - Adicionar novo Administrador \n2 - Ver Livros disponíveis");
        System.out.println("3 - Remover Livros \n4 - Adicionar um livro novo");
        System.out.println("5 - Livros reservados\n6 - Datas dos Livros Reservados");
        System.out.println("7 - Ver datas de Livros em atraso\n8 - Sair");
        System.out.println("Escolha a opção: ");
        int opcao = scn.nextInt();
        cont.recebeOpcaoADM(opcao);
    }

    public void adicionaLivroNovo(Livros liv){
        Scanner scn = new Scanner(System.in);
        String nomeLivroNovo;
        int anoDoLivroNovo;
        System.out.println("Nome do novo livro: ");
        nomeLivroNovo = scn.next();
        System.out.println("Ano do livro: ");
        anoDoLivroNovo = scn.nextInt();
        liv.adicionarLivros(nomeLivroNovo, anoDoLivroNovo);
    }

    public void adicionaAdministrador(){
        ControllerUsuario cont = new ControllerUsuario();
        Scanner scn = new Scanner(System.in);
        String loginADM;
        int senhaADM;
        System.out.println("Login do novo Administrador:");
        loginADM = scn.next();
        System.out.println("Senha do novo Administrador: ");
        senhaADM = scn.nextInt();
        cont.chamaUsuario(loginADM, senhaADM);
    }

    public void removerLivros(Livros liv){
        Scanner scn = new Scanner(System.in);
        String nomeLivroExcluido;
        System.out.println("Qual o nome do livro que deseja excluir??");
        nomeLivroExcluido = scn.next();
        liv.excluirLivro(nomeLivroExcluido);
    }

    public void menuAluno(Usuario usu){
        Scanner scn = new Scanner(System.in);
        Controller cont = new Controller();
        Livros liv = new Livros();
        System.out.println("Entrou na conta como Aluno, o que deseja fazer: \n");
        System.out.println("1 - Livros disponíveis\n2 - Reservado");
        System.out.println("3 - Data de entrega\n4 - Renovação");
        int option = scn.nextInt();
        cont.recebeOpcaoAluno(option, usu, liv);
    }

    public void reservaLivro(Usuario usu, Livros liv){
        Scanner scn = new Scanner(System.in);
        String reservado;
        System.out.println("Nome do livro que deseja reservar: ");
        reservado = scn.next();
        int livrosNaoReservados = liv.veSeOLivroJaEstaReservado(reservado);
        if(livrosNaoReservados == 0){
            System.out.println("Este livro ja foi reservado");
        } else{
            int loginALuno = liv.pegarIDdoAluno(usu.getLogin());
            liv.reservaDoLivro(livrosNaoReservados, loginALuno);
        }
    }

    public void cadastroNovoAluno(){
        Scanner scn = new Scanner(System.in);
        ControllerUsuario cont = new ControllerUsuario();
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
        cont.adicionarAluno(loginNovoAluno, senhaNovoAluno, nomeNovoAluno, emailNovoAluno);
    }
}
