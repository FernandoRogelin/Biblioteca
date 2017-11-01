package br.com.fernando.controller;

import br.com.fernando.view.Main;
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
                main.adicionaAdministrador(adm);
                break;
            case 2:
                liv.verLivrosDisponiveis();
                break;
            case 3:
                main.removerLivros(adm, liv);
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

    public void recebeOpcaoAluno(int opcao, Usuario usu){
        switch (opcao){
            case 1:
                liv.verLivrosDisponiveis();
                break;
            case 2:
                main.reservaLivro(usu, alu, liv);
                break;
            case 3:
                int idAluno = alu.pegarIDdoAluno(usu.getLogin());
                alu.dataDeEntregaDoLivro(idAluno);
                break;
            case 4:
                break;
        }
    }
}
