/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 * @version 0.1
 * @author Décio Antonio de Carvalho
 * @since 16/09/2016
 */
public class Usuario {
    
    private String login;
    private String senha;
    private int poder;

    public Usuario() {
    }
/**
 * 
 * @param login
 * @param senha
 * @param poder 
 */
    public Usuario(String login, String senha, int poder) {
        this.login = login;
        this.senha = senha;
        this.poder = poder;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }
    
    

}

