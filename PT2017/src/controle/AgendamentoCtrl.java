/*
 * Este Software tem Objetivo Educacional
 * Para fins de aprendizagem e avaliacao na
 * Na Disciplina de Laboratório Engenharia
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - julho a Dezembro 2016 
 * Aluno Decio Antonio de Carvalho  
*/


/**
 * Métodos desta classe
 * 
 *  01 - receberIdAgendamentoAtual() //retorna int id
 *  02 - inserirAgendamentoCtrl() 
 *  03 - alterarAgendamentoCtrl()
 *  04 - excluirAgendamentoCtrl()
 *  05 - contarAgendamentoHorarioCtrl()
 *  06 - listarAgendamentoHorarioCtrl();
 *  07 -
 *  08 - listarAgendamentoCtrl();
 *  09 - listarAgendamentoClienteCtrl()
 *  10 - 
 */
 
package controle;


import java.util.List;
import modelo.Agendamento;
import modelo.AgendamentoDAO;


/**
 *
 * @author Décio
 */
public class AgendamentoCtrl {
   
    /**
     * 1
     * Método controle receber o id do último agendamento cadastrado.
     * se não existir o id atual será 0
     * @return último id agendado
     */
    public int receberIdAgendamentoAtual(){
     AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
     int idAgendamento = agendamentoDAO.buscarIdAgendamentoAtual();
     return idAgendamento;
    }//Final método receberAgendamentoAtual
    
    /**
     * 2
     * Método controle enviar agendamento para cadastro
     * @param agendamento
     */
    public void inserirAgendamentoCtrl(Agendamento agendamento){
      AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
      agendamentoDAO.inserirAgendamento(agendamento);
    }//Final método inserirAgendamentoCtrl
    
    /**
     * 3
     * Método controle para enviar agendamento para alteração 
     * enviando o agendamento alterado e seu id
     * @param agendamento
     * @param vid 
     */
    public void alterarAgendamentoCtrl(Agendamento agendamento, String vid){
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        agendamentoDAO.alterarAgendamento(agendamento, vid);
        
    }//Final método alterarAgendamentoCtrl
    
    /**
     * 4
     * Método controle para enviar agendamento para exclusão
     * enviando o seu id
     * @param vid 
     */
    public void excluirAgendamentoCtrl(int vid){
       AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        agendamentoDAO.excluirAgendamento(vid);
    }//Final método excluirAgendamentoCtrl
    
    /**
     * 5
     * Método que executa a contagem dos registros que atendem
     * ao dia e hora enviados.
     * @param vData
     * @param vHora
     * @return int total de agendamentos do horário
     */
    public int contarAgendamentoHorarioCtrl(String vData, String vHora){
        int resposta;
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        resposta = agendamentoDAO.contarAgendamentosHorario(vData, vHora);
        return resposta;
    }//Final método contarAgendamentoHorarioCtrl
    
    /**
     * 6
     * Método que recebe uma lista de agendamento de determinado
     * dia e horário, enviando estes dados dia e horário.
     * @param vData
     * @param vHora
     * @return 
     */
    public List listarAgendamentoHorarioCtrl(String vData, String vHora){
     AgendamentoDAO agendamentoDAO;
     agendamentoDAO = new AgendamentoDAO();
     List<Agendamento> lista = agendamentoDAO.listarAgendamentoHorario(vData, vHora);
     return lista;
    }//Final método listarAgendamentoHorarioCtrl
    
    /**
     * 9
     * Método que recebe uma lista de agendamento de determinado
     * Cliente enviando como parâmetro o seu respectivo id.
     * @param vid
     * @return 
     */
    public List listarAgendamentosCtrl(){
     AgendamentoDAO agendamentoDAO;
     agendamentoDAO = new AgendamentoDAO();
     List<Agendamento> lista = agendamentoDAO.listarAgendamentos();
     return lista;
    }//Final método listarAgendamentosCtrl
    
    /**
     * 9
     * Método que recebe uma lista de agendamento de determinado
     * Cliente enviando como parâmetro o seu respectivo id.
     * @param vid
     * @return 
     */
    public List listarAgendamentoClienteCtrl(String vid){
     AgendamentoDAO agendamentoDAO;
     agendamentoDAO = new AgendamentoDAO();
     List<Agendamento> lista = agendamentoDAO.listarAgendamentoCliente(vid);
     return lista;
    }//Final método listarAgendamentoClienteCtrl
    
    
}//Final classe AgendamentoCtrl
