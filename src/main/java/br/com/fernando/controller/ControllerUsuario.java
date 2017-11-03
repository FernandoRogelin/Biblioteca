package br.com.fernando.controller;

import br.com.fernando.biblioteca.*;
import br.com.fernando.view.View;

public class ControllerUsuario {

    AdministradorDAO adm = new AdministradorDAO();
    AlunoDAO alu = new AlunoDAO();
    View view = new View();

    public ControllerUsuario() {
    }

    public void chamaUsuario(String login, int senha){
        Usuario usu = new Usuario();
        usu.setLogin(login);
        usu.setSenha(senha);
        AdministradorDAO adm = new AdministradorDAO();
        adm.adicionarAdministrador(usu);
    }

    public void verificaConta(String login, int senha){
        Usuario usu = new Usuario();
        usu.setLogin(login);
        usu.setSenha(senha);
        if(adm.verContaAdministrador(usu)){
            view.menuAdministrador(usu);
        } else if(alu.verContaAluno(usu)){
            view.menuAluno(usu);
        } else{
            view.cadastroNovoAluno();
        }
    }

    public void adicionarAluno(String login, int senha, String nome, String email){
        Usuario usu = new Usuario();
        usu.setLogin(login);
        usu.setSenha(senha);
        usu.setNome(nome);
        usu.setEmail(email);
        AlunoDAO alu = new AlunoDAO();
        alu.cadastroDeNovoAluno(usu);
    }
}
