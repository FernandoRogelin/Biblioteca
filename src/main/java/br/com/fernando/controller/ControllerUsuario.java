package br.com.fernando.controller;

import br.com.fernando.view.Main;
import br.com.fernando.biblioteca.*;

public class ControllerUsuario {

    AdministradorDAO adm = new AdministradorDAO();
    Aluno alu = new Aluno();
    Main main = new Main();

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
            main.menuAdministrador(usu);
        } else if(alu.verContaAluno(usu)){
            main.menuAluno(usu);
        } else{
            main.cadastroNovoAluno();
        }
    }

    public void adicionarAluno(String login, int senha, String nome, String email){
        Usuario usu = new Usuario();
        usu.setLogin(login);
        usu.setSenha(senha);
        usu.setNome(nome);
        usu.setEmail(email);
        Aluno alu = new Aluno();
        alu.cadastroDeNovoAluno(usu);
    }
}
