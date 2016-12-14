/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import modelo.Animal;
import modelo.AnimalDAO;
import visao.EntradaSistemaPetFast;

/**
 *
 * @author Décio
 */
public class AnimalCtrl {

    public AnimalCtrl() {
    }
   
    /**
     * 1
     * Método controle receber um Animal informando o nome
     * @param nomeAnimal
     * @return Animal
     */
    public Animal receberAnimalNome(String nomeAnimal){
        AnimalDAO animalDAO = new AnimalDAO();
        Animal animal = animalDAO.buscarAnimalNome(nomeAnimal);
        return animal;  
    }
    
    /**
     * 2a
     * 
     * @param nomeAnimal
     * @param IdCliente
     * @return 
     */
    public Animal receberAnimalNomeIdCliente(String nomeAnimal, int IdCliente){
        AnimalDAO animalDAO = new AnimalDAO();
        Animal animal = animalDAO.buscarAnimalNome(nomeAnimal);
        return animal;  
    }
    
    /**
     * 2
     * Método controle receber o id do último animal cadastrado.
     * se não existir o id atual será 0
     * @return ultimo id do animal cadastrado.
     */
    public int receberIdAnimalAtual(){
        AnimalDAO animalDAO = new AnimalDAO();
        int idAnimal= animalDAO.buscarIdPetAtual();
        return idAnimal; 
    }
    
    /**
     * 3
     * Método controle receber lista de animais pertencentes a um cliente 
     * informando o id do cliente.
     * @param idCliente
     * @return Lista de animais do cliente
     */
    public List<Animal> receberListaAnimaisCliente(int idCliente){
        AnimalDAO animalDAO = new AnimalDAO();
        List<Animal> lista = animalDAO.listarAnimaisCliente(idCliente);
        return lista;
    }
    
    /**
     * 4
     * Método controle receber resposta se existe o animal informando seu id
     * @param idAnimal
     * @return resposta true or false
     */
    public boolean receberAnimalByIdAnimal(int idAnimal){
       AnimalDAO animalDAO = new AnimalDAO();
       Boolean resposta = animalDAO.buscarAnimalByIdAnimal(idAnimal);
       return resposta;
    }
    
    /**
     * 5
     * Método controle receber objeto Animal informando o id do animal
     * @param idAnimal
     * @return Animal
     */
    public Animal receberAnimaId(int idAnimal){
       AnimalDAO animalDAO = new AnimalDAO();
       Animal animal = animalDAO.buscarAnimalId(idAnimal);
       return animal; 
    }
    
    /**
     * 6
     * Método controle enviar animal para cadastro
     * @param animal 
     */
    public void cInserirAnimal(Animal animal){
        AnimalDAO animalDAO = new AnimalDAO();
        animalDAO.inserirAnimal(animal);        
    }//Final método cInserirAnimal
    
    /**
     * 7
     * Método controle para enviar alteração de animal cadastrado
     * @param animal 
     */
    public void cAlterarAnimal(Animal animal, String id){
        AnimalDAO animalDAO = new AnimalDAO();
        animalDAO.alterarAnimal(animal, id);        
    }//Final método cAlterarAnimal
    
    /**
     * 8
     * Método controle para enviar remoção de animal cadastrado
     * @param animal 
     */
    public void cDelerarAnimal(String nomeAnimal, int id){
        AnimalDAO animalDAO = new AnimalDAO();
        animalDAO.deletarAnimal(nomeAnimal, id);        
    }//Final método cDeletarAnimal
    
    /**
     * 9
     */
    public void chamarTelaAnimal(){
        System.out.println("Estou no método: chamarTelaAnimal");
        
        EntradaSistemaPetFast telaPrincipal = new EntradaSistemaPetFast();
        telaPrincipal.chamarTelaAnimal();
    }//Final método chamarTelaAnimal
    
     /**
     * 10
     * Método controle receber lista de animais pertencentes a um cliente 
     * informando o id do cliente.
     * @param idCliente
     * @return Lista de animais do cliente
     */
    public ArrayList<Animal> receberArrayListAnimaisCliente(int idCliente){
        AnimalDAO animalDAO = new AnimalDAO();
        ArrayList<Animal> lista = animalDAO.ArraylistAnimaisCliente(idCliente);
        return lista;
    }
    
    /**
     * 11
     * Método populaComboAnimaisCliente
     * @param id
     * @return 
     */
    public ArrayList populaComboAnimaisCliente(int id) {
       ArrayList lista;
        AnimalDAO animalDAO = new AnimalDAO();
        return lista = animalDAO.findComboAnimalNome(id);
        //return lista = aeronaveDAO.findComboAeronave();
   
    }
    
    /**
     * Método controle para contar a quantidade de animais do cliente
     * @param cliente
     * @return 
     */
    public int ReceberContarAnimaisCliente(int cliente) {
    AnimalDAO animalDAO = new AnimalDAO();
    int resposta = animalDAO.contarAnimaisCliente(cliente);
    return resposta;
    }

    /**
     * Método retorna uma lista com os animais que contem no nome a string 
     * passada no parametro
     * @param nomeAnimal
     * @return 
     */
    public List listaAnimaisNome(String nomeAnimal) {
       AnimalDAO animalDAO = new AnimalDAO();
       List<Animal> lista = animalDAO.listarAnimaisNome(nomeAnimal);
       return lista;
        
    }
}//Final classe AnimalCtrl
