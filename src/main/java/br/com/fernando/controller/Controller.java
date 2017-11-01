package br.com.fernando.controller;

import br.com.fernando.biblioteca.*;

public class Controller {
    AdministradorDAO adm = new AdministradorDAO();
    Aluno alu = new Aluno();
    Main main = new Main();
    Livros liv = new Livros();

    public Controller(){

    }

    public void recebeOpcaoADM(int opcao){
        switch (opcao){
            case 1:
                main.adicionaAdministrador();
                break;
            case 2:
                liv.verLivrosDisponiveis();
                break;
            case 3:
                main.removerLivros(liv);
                break;
            case 4:
                main.adicionaLivroNovo(liv);
                break;
            case 5:
                liv.livrosReservados();
                break;
            case 6:
                adm.dataDosLivrosReservados();
                break;
            case 7:
                break;
        }
    }

    public void recebeOpcaoAluno(int opcao, Usuario usu, Livros liv){
        switch (opcao){
            case 1:
                liv.verLivrosDisponiveis();
                break;
            case 2:
                main.reservaLivro(usu, liv);
                break;
            case 3:
                int idAluno = liv.pegarIDdoAluno(usu.getLogin());
                liv.dataDeEntregaDoLivro(idAluno);
                break;
            case 4:
                break;
        }
    }
}
