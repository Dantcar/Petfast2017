/*
 * Este Software tem Objetivo Educacional
 * Para fins de aprendizagem e avaliacao na
 * Na Disciplina de Laboratório Engenharia
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - julho a Dezembro 2016 
 * Aluno Decio Antonio de Carvalho  * 
 */
package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.ClienteDAO;

/**
 *
 * @author Dac
 */
public class ClienteCtrl {
    private Object CliDAO;
    
    public void receberCliente(Cliente cliente) throws ClassNotFoundException, SQLException{
        ClienteDAO cliDAO = new ClienteDAO();
        cliDAO.inserirNovoCliente(cliente);
    }
     
    public static Cliente receberClienteCPF(String cpf) throws ClassNotFoundException, SQLException{
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cliente = cliDAO.buscarClienteCPF(cpf);
        return cliente;
    }
     
    public static boolean receberPesquisarClienteCPF(String cpf){
         boolean resposta = false;
         ClienteDAO cliente = new ClienteDAO();
         resposta = cliente.buscarExisteClienteCPF(cpf);
        return resposta;
    }
     
    public void alterarClienteCtrl(Cliente cliente, String cpf) throws ClassNotFoundException, SQLException{
        ClienteDAO cliDAO = new ClienteDAO();
        cliDAO.alterarCliente(cliente, cpf);
    } 
     
    public void deletarClienteCtrl(Cliente cliente, String cpf) throws ClassNotFoundException, SQLException{
        ClienteDAO cliDAO = new ClienteDAO();
        cliDAO.deletarCliente(cliente, cpf);
    } 

    public ArrayList populaComboClienteNome() {
       ArrayList lista;
       ClienteDAO clienteDAO = new ClienteDAO();
       return lista = clienteDAO.findComboClienteNome();
    
    }
    
    /**
     * Método Controle para receber uma lista de clientes
     * @return 
     */
    public List<Cliente> listarClientes() {
       ClienteDAO clienteDAO = new ClienteDAO();
       List<Cliente> lista = clienteDAO.listarClientes();
       return lista;
    
    }

    /**
     * Método Controle para receber a busca de uma lista de clientes
     * enviando o nome
     * @param nomeCliente
     * @return 
     */
    public List<Cliente> listaClientesNome(String nomeCliente) {
       ClienteDAO clienteDAO = new ClienteDAO();
       List<Cliente> lista = clienteDAO.listarClienteNome(nomeCliente);
       return lista;
    
    }
    
    /**
     * Método Controle para receber a busca de um cliente enviando o nome
     * @param nomeCliente
     * @return 
     */
    public static Cliente receberClienteNome(String nomeCliente){
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cliente = cliDAO.buscarClienteNome(nomeCliente);
        return cliente;   
        
    }
    
    /**
     * Método Controle para receber a busca de um cliente enviando o telefone
     * @param telefoneCliente
     * @return 
     */
     public static Cliente receberClienteTelefone(String telefoneCliente){
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cliente = cliDAO.buscarClienteTelefone(telefoneCliente);
        return cliente;   
        
    }
     
     /**
     * Método Controle para receber a busca de uma lista de clientes
     * enviando o nome
     * @param nomeCliente
     * @return 
     */
    public List<Cliente> listaClientesPorNome(String nomeCliente) {
       ClienteDAO clienteDAO = new ClienteDAO();
       List<Cliente> lista = clienteDAO.listarClienteNome(nomeCliente);
       return lista;
    
    }
    
     public Cliente buscarClientesPorNome(String nomeCliente) {
       ClienteDAO clienteDAO = new ClienteDAO();
       Cliente cli = clienteDAO.buscarClientePorNome(nomeCliente);
       return cli;
    
    }

    public String buscarNomeId(String idCliente) {
        String cli;
        ClienteDAO clienteDAO = new ClienteDAO();
        cli = clienteDAO.buscarClientePorId(idCliente);
       return cli;   
    }
    
    public Cliente buscarNomeIdInt(int idCliente) {
        Cliente cli;
        ClienteDAO clienteDAO = new ClienteDAO();
        cli = clienteDAO.buscarClientePorIdInt(idCliente);
       return cli;   
    }
        
}
