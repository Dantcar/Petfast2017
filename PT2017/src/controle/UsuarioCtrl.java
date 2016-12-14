/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;


import modelo.UsuarioDAO;


/**
 * @version 
 * @author Décio Antonio de Carvalho
 * @since 
 */
public class UsuarioCtrl {

//buscar usuario login
    public static boolean logarUsuario(String login, String password){
         boolean resposta = false;
         UsuarioDAO usuario = new UsuarioDAO();
         resposta = usuario.logarPetfast(login, password);
        return resposta;
    }
}
