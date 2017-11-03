package br.com.fernando.controller;

import br.com.fernando.biblioteca.*;
import br.com.fernando.view.View;

public class Controller {
    AdministradorDAO adm = new AdministradorDAO();
    AlunoDAO alu = new AlunoDAO();
    View view = new View();
    LivrosDAO liv = new LivrosDAO();

    public Controller(){

    }

    public void recebeOpcaoADM(int opcao){
        switch (opcao){
            case 1:
                view.adicionaAdministrador(adm);
                break;
            case 2:
                liv.verLivrosDisponiveis();
                break;
            case 3:
                view.removerLivros(adm, liv);
                break;
            case 4:
                view.adicionaLivroNovo(liv);
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
                view.reservaLivro(usu, alu, liv);
                break;
            case 3:
                int idAluno = alu.pegarIDdoAluno(usu.getLogin());
                alu.dataDeEntregaDoLivro(idAluno);
                break;
            case 4:
                view.renovacao(alu, liv, usu);
                break;
        }
    }
}
