package br.com.fernando.view;

import br.com.fernando.biblioteca.AdministradorDAO;
import br.com.fernando.biblioteca.AlunoDAO;
import br.com.fernando.biblioteca.LivrosDAO;
import br.com.fernando.biblioteca.Usuario;
import br.com.fernando.controller.Controller;
import br.com.fernando.controller.ControllerUsuario;

import java.util.Scanner;

public class View {

    public View() {
    }

    public void menuAdministrador(Usuario usu){
        Scanner scn = new Scanner(System.in);
        Controller cont = new Controller();
        int i = 0;
        while(i == 0){
            System.out.println("\nEntrada na conta como Administrador \n");
            System.out.println("1 - Adicionar novo Administrador \n2 - Ver Livros disponíveis");
            System.out.println("3 - Remover Livros \n4 - Adicionar um livro novo");
            System.out.println("5 - Livros reservados\n6 - Datas dos Livros Reservados");
            System.out.println("7 - Sair");
            System.out.println("Escolha a opção: ");
            int opcao = scn.nextInt();
            if(opcao == 7){
                i = 1;
            } else{
                cont.recebeOpcaoADM(opcao);
            }
        }
    }

    public void adicionaLivroNovo(LivrosDAO liv){
        Scanner scn = new Scanner(System.in);
        String nomeLivroNovo;
        String anoDoLivroNovo;
        System.out.println("Nome do novo livro: ");
        nomeLivroNovo = scn.nextLine();
        System.out.println("Ano do livro: ");
        anoDoLivroNovo = scn.nextLine();
        while (!anoDoLivroNovo.matches("[0-9]+")) {
            System.out.println("Deve colocar só números");
            System.out.println("Digite o Ano de novo: ");
            anoDoLivroNovo = scn.nextLine();
        }
        System.out.println("Livro adicionado com sucesso");
        int idNovoLivro = Integer.parseInt(anoDoLivroNovo);
        liv.adicionarLivros(nomeLivroNovo, idNovoLivro);
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

    public void removerLivros(AdministradorDAO adm, LivrosDAO liv){
        Scanner scn = new Scanner(System.in);
        String nomeLivroExcluido;
        System.out.println("Qual o nome do livro que deseja excluir??");
        nomeLivroExcluido = scn.nextLine();
        int idLivro = liv.idDoLivro(nomeLivroExcluido);
        if(adm.verificaLivroParaExcluir(idLivro)){
            System.out.println("Livro reservado, não pode ser exluido");
        } else{
            System.out.println("Livro excluido com sucesso");
            adm.excluirLivro(idLivro);
        }
    }

    public void menuAluno(Usuario usu){
        Scanner scn = new Scanner(System.in);
        Controller cont = new Controller();
        int i = 0;
        while(i == 0){
            System.out.println("Entrada na conta como AlunoDAO, o que deseja fazer: \n");
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

    public void renovacao(AlunoDAO alu, LivrosDAO liv, Usuario usu){
        Scanner scn = new Scanner(System.in);
        String nomeLivro;
        System.out.println("Nome do livro para renovacao: ");
        nomeLivro = scn.next();
        int idLivro = liv.idDoLivro(nomeLivro);
        int idAluno = alu.pegarIDdoAluno(usu.getLogin());
        liv.renovacaoDeLivro(idAluno, idLivro);
    }

    public void reservaLivro(Usuario usu, AlunoDAO alu, LivrosDAO liv){
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
        String nomeNovoAluno, emailNovoAluno, loginNovoAluno, senhaNovoAluno;
        System.out.println("Login: ");
        loginNovoAluno = scn.next();
        System.out.println("Senha: ");
        senhaNovoAluno = scn.next();
        while (!senhaNovoAluno.matches("[0-9]+")) {
            System.out.println("Deve colocar só números");
            System.out.println("Digite a Senha novamente: ");
            senhaNovoAluno = scn.nextLine();
        }
        int senhaNova = Integer.parseInt(senhaNovoAluno);
        System.out.println("Nome: ");
        nomeNovoAluno = scn.next();
        System.out.println("E-mail");
        emailNovoAluno = scn.next();
        cont.adicionarAluno(loginNovoAluno, senhaNova, nomeNovoAluno, emailNovoAluno);
    }
}
