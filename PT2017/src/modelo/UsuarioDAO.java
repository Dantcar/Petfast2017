/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package modelo;

/**
 * @version 
 * @author Décio Antonio de Carvalho
 * @since 
 */

 
import static controle.Util.reduzString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    private Connection conexao;
    private Statement stmt;
    private ResultSet rs;

    public UsuarioDAO() {
    }
    
  

    public UsuarioDAO(Connection conexao, Statement stmt, ResultSet rs) {
        this.conexao = conexao;
        this.stmt = stmt;
        this.rs = rs;
    }
    
    
    
    /**
     * método para encerrar Connection, Statement e ResutlSet
     */  
    private void close() {
        try {
            if (rs != null) {
            rs.close();
            }
            
            if (stmt != null) {
            stmt.close();
            }
            
            if (conexao != null) {
            conexao.close();
            }
        } catch (Exception e) {
          String msg = "" + e;
          JOptionPane.showMessageDialog(null, reduzString(msg));
    }
    }//fim close() 
    
    /*
    select * from DAC.USUARIO
    WHERE LOGIN = 'Teste' AND SENHA = '12345abc';
    */
    public boolean logarPetfast(String user, String password){
        boolean resposta = false ;
        
        String msg, sql;
        msg="";
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        sql = "SELECT * FROM usuario WHERE LOGIN = '"+user +"' AND SENHA ='" + password + "'";
        System.out.println(sql);
            try {
                 stmt =conexao.createStatement(
                 ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_READ_ONLY);
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, reduzString(msg + ex + sql));
                 Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                rs = stmt.executeQuery(sql); 
                //select * from DAC.USUARIO WHERE LOGIN = 'Teste' AND SENHA = '12345abc';
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, reduzString(msg + ex+ sql));
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
     try {
         if (rs.first()) {
           //entra no sistema
             resposta = true;
             close();
            } else{
          // msg = "tentativa "  + tentativa +"de 3".;
         }
     } catch (SQLException ex) {
         Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
         msg=""+ex;
         JOptionPane.showMessageDialog(null, reduzString(msg));
         resposta = false;
         close();
     }
     
     return resposta;
    }//final metodo realizarLoginPetfast
    
    
    
}//FINAL CLASSE UsuarioDAO

