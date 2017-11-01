package br.com.fernando.view;

import br.com.fernando.biblioteca.AdministradorDAO;
import br.com.fernando.biblioteca.Aluno;
import br.com.fernando.biblioteca.Livros;
import br.com.fernando.biblioteca.Usuario;
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
        int i = 0;
        while(i == 0){
            System.out.println("Entrada na conta como Administrador \n");
            System.out.println("1 - Adicionar novo Administrador \n2 - Ver Livros disponíveis");
            System.out.println("3 - Remover Livros \n4 - Adicionar um livro novo");
            System.out.println("5 - Livros reservados\n6 - Datas dos Livros Reservados");
            System.out.println("7 - Ver datas de Livros em atraso\n8 - Sair");
            System.out.println("Escolha a opção: ");
            int opcao = scn.nextInt();
            if(opcao == 8){
                i = 1;
            } else{
                cont.recebeOpcaoADM(opcao);
            }
        }
    }

    public void adicionaLivroNovo(Livros liv){
        Scanner scn = new Scanner(System.in);
        String nomeLivroNovo;
        int anoDoLivroNovo;
        System.out.println("Nome do novo livro: ");
        nomeLivroNovo = scn.next();
        System.out.println("Ano do livro: ");
        anoDoLivroNovo = scn.nextInt();
        System.out.println("Livro adicionado com sucesso");
        liv.adicionarLivros(nomeLivroNovo, anoDoLivroNovo);
    }

    public void adicionaAdministrador(AdministradorDAO adm){
        ControllerUsuario cont = new ControllerUsuario();
        Scanner scn = new Scanner(System.in);
        String loginADM;
        int senhaADM;
        System.out.println("Login do novo Administrador:");
        loginADM = scn.next();
        System.out.println("Senha do novo Administrador: ");
        senhaADM = scn.nextInt();
        if(adm.verificaLoginADM(loginADM)){
            System.out.println("Login de administrador já existe");
        } else {
            System.out.println("Adicionado novo administrador");
            cont.chamaUsuario(loginADM, senhaADM);
        }

    }

    public void removerLivros(AdministradorDAO adm, Livros liv){
        Scanner scn = new Scanner(System.in);
        String nomeLivroExcluido;
        System.out.println("Qual o nome do livro que deseja excluir??");
        nomeLivroExcluido = scn.next();
        int idLivro = liv.idDoLivro(nomeLivroExcluido);
        if(adm.verificaLivroParaExcluir(idLivro)){
            System.out.println("Livro reservado, não pode ser exluido");
        } else{
            System.out.println("Livro excluido com sucesso");
            adm.excluirLivro(nomeLivroExcluido);
        }

    }

    public void menuAluno(Usuario usu){
        Scanner scn = new Scanner(System.in);
        Controller cont = new Controller();
        int i = 0;
        while(i == 0){
            System.out.println("Entrada na conta como Aluno, o que deseja fazer: \n");
            System.out.println("1 - Livros disponíveis\n2 - Reservado");
            System.out.println("3 - Data de entrega\n4 - Renovação");
            System.out.println("5 - Sair");
            int option = scn.nextInt();
            if(option == 5){
                i = 1;
            } else{
                cont.recebeOpcaoAluno(option, usu);
            }
        }
    }

    public void reservaLivro(Usuario usu, Aluno alu, Livros liv){
        Scanner scn = new Scanner(System.in);
        String reservado;
        System.out.println("Nome do livro que deseja reservar: ");
        reservado = scn.next();
        int livrosNaoReservados = liv.veSeOLivroJaEstaReservado(reservado);
        if(livrosNaoReservados == 0){
            System.out.println("Este livro ja foi reservado");
        } else{
            int loginALuno = alu.pegarIDdoAluno(usu.getLogin());
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
