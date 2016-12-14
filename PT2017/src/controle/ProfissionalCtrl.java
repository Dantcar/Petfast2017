/*
 * Este Software tem Objetivo Educacional
 * Para fins de TCC
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - Julho a Dezembnro  
 * Aluno Decio Antonio de Carvalho  * 
 * 24-10-2016
 */


package controle;

import java.util.List;
import modelo.Profissional;
import modelo.ProfissionalDAO;



/**
 * @version 
 * @author Dantcar
 * @since 24-10-2016
 */
public class ProfissionalCtrl {

    public ProfissionalCtrl() {
    }
    
   /**
    * 1
    * @param nomeProfissional
    * @return 
    */
   public Profissional receberProfissionalNome(String nomeProfissional){
        //AnimalDAO animalDAO = new AnimalDAO();
        ProfissionalDAO profDAO = new ProfissionalDAO();
        
        Profissional profissional = profDAO.buscarProfissionalNome(nomeProfissional);
        return profissional;  
    } 
   
    /**
     * 2
     * Método controle receber o id do último profissional cadastrado.
     * se não existir o id atual será 0
     * @return ultimo id do profissional cadastrado.
     */
    public int receberIdProfissionalAtual(){
        ProfissionalDAO profDAO = new ProfissionalDAO();
        int idProfissional = profDAO.buscarIdProfissionalAtual();
        return idProfissional; 
    }
    
    /**
     * 3
     * Método controle receber resposta se existe o animal informando seu id
     * @param idProfissional
     * @return resposta true or false
     */
    public boolean receberProfissionalByIdProfissional(int idProfissional){
       ProfissionalDAO profDAO = new ProfissionalDAO();
       Boolean resposta = profDAO.buscarProfissionalByIdProfissional(idProfissional);
       return resposta;
    }
    
    /**
     * 4
     * Método controle receber objeto Profissional informando o id do profissional
     * @param idProfissional
     * @return Animal
     */
    public Profissional receberProfissionalId(int idProfissional){
       ProfissionalDAO profDAO = new ProfissionalDAO();
       Profissional animal = profDAO.buscarProfissionalId(idProfissional);
       return animal; 
    }
    
     /**
     * 5
     * Método controle enviar animal para cadastro
     * @param profissional
     */
    public void cInserirProfissional(Profissional profissional){
        ProfissionalDAO profDAO = new ProfissionalDAO();
        profDAO.inserirProfissional(profissional);
    }//Final método cInserirProfissional
    
     /**
     * 6
     * Método controle para enviar alteração de animal cadastrado
     * @param profissional, id
     */
    public void cAlterarProfissional(Profissional profissional, String id){
        ProfissionalDAO profDAO = new ProfissionalDAO();
        profDAO.alterarProfissional(profissional, id);
    }//Final método cAlterarProfissional
    
     /**
     * 8
     * Método controle para enviar remoção de animal cadastrado
     * @param animal 
     */
    public void cDelerarProfissional(String nomeProfissional, int id){
        ProfissionalDAO profDAO = new ProfissionalDAO();
        profDAO.deletarProfissional(nomeProfissional, id);
    }//Final método cDeletarAnimal
    

     /**
     * Método retorna uma lista com os animais que contem no nome a string 
     * passada no parametro
     * @param nomeAnimal
     * @return 
     */
    public List listaProfissionalNome(String nomeProfissional) {
       ProfissionalDAO profDAO = new ProfissionalDAO();
       List<Profissional> lista = profDAO.listarProfissionaisNome(nomeProfissional);
       return lista;
        
    }
    
}
