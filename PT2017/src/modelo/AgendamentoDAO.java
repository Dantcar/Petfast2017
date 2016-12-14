/*
 * Este Software tem Objetivo Educacional
 * Para fins de aprendizagem e avaliacao na
 * Na Disciplina de Laboratório Engenharia
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - julho a Dezembro 2016 
 * Aluno Decio Antonio de Carvalho  * 
 */

/**
 * Métodos desta classe
 * 
 *  ok  01 - buscarIdAgendamentoAtual() //retorna int id
 *  ok  02 - inserirAgendamento();
 *  ok  03 - alterarAgendamento();
 *  ok  04 - excluirAgendamento();
 *  ok  05 - contarAgendamentosHorario(); /retorna número de agendamentos no horário
 *  ok  06 - listarAgendamentoHorario();  
 * 07 - listarAgendamentoSemana()
 *  ok  08 - listaAgendamentos();
 *  ok  09 - listarAgendamentoCliente(); //dia ou periodo
 * 10 - listarAgendamentoAnimalDia();
 * 
 */

package modelo;

import static controle.Util.reduzString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @version 
 * @author Dantcar
 * @since 
 */
public class AgendamentoDAO {
    private Connection conexao;
    private Statement stmt;
    private ResultSet rs;
    public int idAgendamentoNow = 0;

    public AgendamentoDAO(Connection conexao, Statement stmt, ResultSet rs) {
        this.conexao = conexao;
        this.stmt = stmt;
        this.rs = rs;
    }

    public AgendamentoDAO() {
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
    
    /**
     * método 01 que busca o idAtual do agendamento, utilizado para 
     * serializar a tabela agendamento.
     * @return idAgendamento
     */
    public int buscarIdAgendamentoAtual(){
        int resposta = 0;
        String msgOk, msg,sql;
        msgOk="";
        msg="";
        
        sql="SELECT * FROM agendamento ORDER BY 1 DESC";
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        
        try {
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,       
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = "" + ex;
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if (rs.first()) {
                idAgendamentoNow = rs.getInt(1);
                resposta = idAgendamentoNow;

                close();
            }
        } catch (SQLException ex) {
            msg = "" + ex;
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            close();
        }
        
        return resposta;
    }//Final método buscarIdAgendamentoAtual
    
    /**
     * Método 02
     * @param agendamento
     */
    public void inserirAgendamento(Agendamento agendamento){
       String msgOk, msg,sql;
      
      msg="";
      msgOk="";
      
      conexao = DBPetFast.getConnection();
      
      sql = "INSERT INTO agendamento VALUES ("
        + agendamento.getIdAgendamento() +", "
        + "'" + agendamento.getDataAgendamento() + "', "
        + "'" + agendamento.getHoraAgendamento() + "', "
        + agendamento.getAnimalId() +", "
        + agendamento.getClienteId() +", "
        + "'" + agendamento.getServico() + "', "
        + agendamento.getIdServico() +", "
        + agendamento.getIdProfissional()+
       ")";
        
      //Preparação do comando sql
        System.out.println(sql);
        
        try {
            stmt = conexao.createStatement();
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        try {
            stmt.executeUpdate(sql);
            msgOk = msgOk +"Dados do agendamento inseridos com sucesso \n";
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
             JOptionPane.showMessageDialog(null,msg );
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         close();
         
         if (!"".equals(msgOk)){
           JOptionPane.showMessageDialog(null, msgOk);  
         }else{
           JOptionPane.showMessageDialog(null, msg);  
         }
         
    }//final método inserirAgendamento
    
    
    /**
     * Método 03 responsável em realizar a alteração de Agendamento
     * @param agendamento
     * @param vid 
     */
    public void alterarAgendamento(Agendamento agendamento, String vid){
        String msg, msgOk;
        
        msg = "";
        msgOk = "";
        
        conexao = DBPetFast.getConnection();
        
        try {
            stmt = conexao.createStatement();
        } catch (SQLException ex) {
            msg = msg + ex;
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql ="UPDATE agendamento SET"
                + "idAgendamento = " + agendamento.getIdAgendamento() + ", "
                + "dataAgendamento = " + agendamento.getDataAgendamento()+ ", "
                + "horaAgendamento = " + agendamento.getHoraAgendamento() + ", "
                + "animalId = " + agendamento.getAnimalId() + ", "
                + "clienteId = " + agendamento.getClienteId() + ", "
                + "servico = " + agendamento.getServico() + ", "
                + "idServico = " + agendamento.getServico() + ", "
                + "idProfessional = " + agendamento.getIdProfissional()
                +  " WHERE IDAGENDAMENTO = " + Integer.parseInt(vid);
        
        System.out.println(sql);
        
        try {
            stmt.executeUpdate(sql);
            msgOk = msgOk + "Dados do agendamento alterados com sucesso \n";
       
        } catch (SQLException ex) {
             msg = msg + ex;
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        close();
        
        if (!"".equals(msgOk)){
           JOptionPane.showMessageDialog(null, msgOk);  
        }else{
           JOptionPane.showMessageDialog(null, msg);  
        }
        
    }//final método alterarAgendamento
    
    /**
     * Método 04 excluir agendamento
     * @param vid 
     */
    public void excluirAgendamento(int vid){
        String msg, msgOk;

        msg = "";
        msgOk = "";
        
        conexao = DBPetFast.getConnection();
        
        try {
            stmt = conexao.createStatement();
        } catch (SQLException ex) {
            msg = msg + ex;
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "DELETE FROM agendamento WHERE idAgendamento = " + vid ;
        
         System.out.println(sql);
         
         int n = JOptionPane.showConfirmDialog(
            null,
            "Confirma Deletar Agendamento?",
            "Confirmar Deletar Agendamento",
            JOptionPane.YES_NO_OPTION);
           
            if(n == 0){
           try {
               stmt.executeUpdate(sql);
               msgOk = msgOk +"Dados do agendamento excluidos com sucesso \n";
                } catch (SQLException ex) {
                msg = reduzString(msg+ex);
                msg = reduzString(msg);
                msg = msg+"Erro de gravação no BD \n";
               Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
           }else{
              msg = msg+"Dados do animal inalterados \n";  
            }
        
            close();
        
            if (!"".equals(msgOk)){
                JOptionPane.showMessageDialog(null, msgOk);  
            }else{
                JOptionPane.showMessageDialog(null, msg);  
            }
        
    }//Final método excluirAgendamento
    
    /**
     * Método 5 contar quantidade de agendamentos determinado horário
     * retorna número de agendamentos no horário
     * */
    public int contarAgendamentosHorario(String vData, String vHora){
        String msg, msgOk, sql;
        String resultado = "resultado";
        int vresultado = 0;
        msg = "";
        msgOk = "";
        
        /*
        //modelo
        sql = "SELECT COUNT (*) AS " + RESULTADO + " FROM CLIENTE"
        + "WHERE UPPER(NOME) LIKE UPPER('%A%')";
        */
        
        sql = "SELECT COUNT (*) AS " + resultado
                + " FROM agendamento WHERE dataAgendamento = '" + vData +"'"
                + " AND horaAgendamento = '" + vHora+"'";
        
        System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        //listaAnimal = null;
        
        conexao = DBPetFast.getConnection();
        
        try {
            //preparacao para buscar no banco
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /*
         try {
            if (rs.first()) {
                idAgendamentoNow = rs.getInt(1);
                resposta = idAgendamentoNow;

                close();
            }
        } catch (SQLException ex) {
            msg = "" + ex;
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            close();
        }
        */
        try {
            rs = stmt.executeQuery(sql);
            if (rs.first()) {
                vresultado = rs.getInt(1);
                
                close();
            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        
        return vresultado;
    } //Final método contarAgendamentoHorario
    
    /**
     * Método 06 que retorna os agendamentos de uma data e hora específica
     * @param vData
     * @param vHora
     * @return 
     */
    public List<Agendamento> listarAgendamentoHorario(String vData, String vHora){
        Agendamento agendamento;
                
        List<Agendamento> listaAgendamento = new ArrayList<>();
        //lista de agerndamentos na data e hora
        
        String msg, msgOk, sql;
        msg = "";
        msgOk = "";
        sql = "";
        
        sql = "SELECT *"
                + " FROM agendamento WHERE dataAgendamento = " + vData
                + "AND horaAgendamento = " + vHora;
        
        System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        //listaAnimal = null;
        try {
            //preparando a conexao com o banco Petfast
            
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //executando o comando sql
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while (rs.next()) {
                //usando o objeto animal estanciado no início do método
                agendamento = new Agendamento();
                
                agendamento.setIdAgendamento(rs.getInt("idagendamento"));
                agendamento.setDataAgendamento(rs.getString("dataagendamento"));
                agendamento.setHoraAgendamento(rs.getString("horaagendamento"));
                agendamento.setAnimalId(rs.getInt("animalid"));
                agendamento.setClienteId(rs.getInt("clienteid"));
                agendamento.setServico(rs.getString("servico"));
                agendamento.setIdServico(rs.getInt("idservico"));
                agendamento.setIdProfissional(rs.getInt("idprofissional"));
                
                listaAgendamento.add(agendamento);
                
                
            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        
        return listaAgendamento;   
}//Final método listarAgendamentoHorario
    
    /**
     * Método 08 listar todos agendamentos
     * @param vid
     * @return 
     */
    public List<Agendamento> listarAgendamentos(){
      Agendamento agendamento;
                
        List<Agendamento> listaAgendamento = new ArrayList<>();
        //lista de agerndamentos na data e hora
        
        String msg, msgOk, sql;
        msg = "";
        msgOk = "";
        sql = "";
        
        sql = "SELECT *"
            + " FROM agendamento";
        
        System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
      
        
        //listaAnimal = null;
        try {
            //preparando a conexao com o banco Petfast
            
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //executando o comando sql
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while (rs.next()) {
                //usando o objeto animal estanciado no início do método
                agendamento = new Agendamento();
                
                agendamento.setIdAgendamento(rs.getInt("idagendamento"));
                agendamento.setDataAgendamento(rs.getString("dataagendamento"));
                agendamento.setHoraAgendamento(rs.getString("horaagendamento"));
                agendamento.setAnimalId(rs.getInt("animalid"));
                agendamento.setClienteId(rs.getInt("clienteid"));
                agendamento.setServico(rs.getString("serviço"));
                agendamento.setIdServico(rs.getInt("idservico"));
                agendamento.setIdProfissional(rs.getInt("idprofissional"));
                
                listaAgendamento.add(agendamento);
                
                
            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        
        
        return listaAgendamento;
   
    }//Final listarAgendamentos();
    
    /**
     * Método 09 listar agendamento por cliente
     * @param vid
     * @return 
     */
    public List<Agendamento> listarAgendamentoCliente(String vid){
      Agendamento agendamento;
                
        List<Agendamento> listaAgendamento = new ArrayList<>();
        //lista de agerndamentos na data e hora
        
        String msg, msgOk, sql;
        msg = "";
        msgOk = "";
        sql = "";
        
        sql = "SELECT *"
            + " FROM agendamento WHERE clienteid = " + Integer.parseInt(vid);
        
        System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
      
        
        //listaAnimal = null;
        try {
            //preparando a conexao com o banco Petfast
            
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //executando o comando sql
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while (rs.next()) {
                //usando o objeto animal estanciado no início do método
                agendamento = new Agendamento();
                
                agendamento.setIdAgendamento(rs.getInt("idagendamento"));
                agendamento.setDataAgendamento(rs.getString("dataagendamento"));
                agendamento.setHoraAgendamento(rs.getString("horaagendamento"));
                agendamento.setAnimalId(rs.getInt("animalid"));
                agendamento.setClienteId(rs.getInt("clienteid"));
                agendamento.setServico(rs.getString("serviço"));
                agendamento.setIdServico(rs.getInt("idservico"));
                agendamento.setIdProfissional(rs.getInt("idprofissional"));
                
                listaAgendamento.add(agendamento);
                
                
            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        
        
        return listaAgendamento;
   
    }//Final listarAgendamentoCliente
 
}//Final da Classe AgendamentoDAO
