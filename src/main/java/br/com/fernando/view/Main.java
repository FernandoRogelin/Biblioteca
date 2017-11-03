package br.com.fernando.view;
import br.com.fernando.controller.ControllerUsuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String login;
        String senha;
        ControllerUsuario cont = new ControllerUsuario();

        System.out.println("Bem-vindo ao software de Biblioteca, Faça seu Login \n");
        System.out.println("Login: ");
        login = scn.next();
        System.out.println("Senha: ");
        senha = scn.next();
        while (!senha.matches("[0-9]+")) {
            System.out.println("Deve colocar só números");
            System.out.println("Digite a Senha novamente: ");
            senha = scn.nextLine();
        }
        int Senha = Integer.parseInt(senha);
        cont.verificaConta(login, Senha);
    }
}
