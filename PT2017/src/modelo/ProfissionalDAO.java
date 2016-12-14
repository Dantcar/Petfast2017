/*
 * Este Software tem Objetivo Educacional
 * Para fins de TCC
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - Julho a Dezembnro  
 * Aluno Decio Antonio de Carvalho  
 *  24-10-2016
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
 *
 * @author Décio
 */
public class ProfissionalDAO {
    
    //variáveis de campos Classe Profissional
    private Connection conexao;
    private Statement stmt;
    private ResultSet rs;
    public int idProfissionalNow = 0;

    public ProfissionalDAO() {
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
    }//final método close()
    
     /**
     * Método para buscar qual o id atual da tabela Profissional
     *
     * @return idProfissional atual
     */
    @SuppressWarnings("null")
    public int buscarIdProfissionalAtual() {
        //variaveis do método
        int resposta = 0;
        String sql, msg;
        msg = "";

        //Realizando conexao com o banco Petfast
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        
        //sql = "SELECT * FROM animal ORDER BY 1 DESC";

        try {
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery("SELECT * FROM profissional ORDER BY 1 DESC"); //select * from DAC.Profissional order BY 1 DESC
            System.out.println("ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try { 
            if (rs.first()) {
                idProfissionalNow = rs.getInt(1);
                resposta = idProfissionalNow;
                
                System.out.println(idProfissionalNow); //remover depois dos testes
                close();
            }
        } catch (SQLException ex) {
            msg = "" + ex;
            JOptionPane.showMessageDialog(null, reduzString(msg));
            msg = "";
            close();
            resposta = 0;
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resposta;
    }//final método buscarIdProfissionalAtual
    
    /**
     * Método para localizar um profissional passando como paramentro o 
     * nome completo do profissional, o nome pode estar em letra minúscula ou maiuscúla
     * @param nomeProfissional
     * @return objeto profissional 
     */
    public Profissional buscarProfissionalNome(String nomeProfissional) {
        Profissional  prof = new Profissional();

        //variaveis do método
        String msg = "";
        String sql = "SELECT * FROM profissional WHERE upper(nome) LIKE upper('" + nomeProfissional + "')";
        System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;

        try {
            //preparacao para buscar no banco
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Buscando no banco a instrucao/comando
        try {
            if (rs.first()) {
                //utilizando o objeto animal estanciado no início do método

                prof.setIdProfissional(rs.getString("idprofissional"));
                prof.setNome(rs.getString("nome"));
                prof.setFotoProfissional(rs.getString("fotoprofissional"));
                prof.setCelular(rs.getString("celular"));
                prof.setEmail(rs.getString("email"));
                prof.setCpf(rs.getString("cpf"));
                prof.setRg(rs.getString("rg"));
                prof.setNascimento(rs.getString("nascimento"));
                prof.setContato(rs.getString("contato"));
                prof.setTelefoneContato(rs.getString("telefonecontato"));
               
            } else {
                msg = msg + "Profissional não encontrado \n";
            }
        } catch (SQLException ex) {

            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //envia mensagem na tela caso ocorra alguma execessao ou nao encontre o profissional
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return prof;

    }//final método buscarProfissionalNome
    
    /**
     * Método boolean que retorna true se existir o profissional informado pelo
     * idprofissional
     *
     * @param idprofissional
     * @return
     */
    public boolean buscarProfissionalByIdProfissional(int idprofissional) {
        boolean resposta = false;
        String msg, sql;
        msg = "";
        sql = "SELECT * FROM profissional WHERE idprofissional = " + idprofissional;
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;

        try {
            //conexao com o banco Petfast
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Enviando o comando sql
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Utilizando o resultado da consulta sql
        try {
            if (rs.first()) {
                close();
                resposta = true;
            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            close();
            resposta = false;

        }

        //envia mensagem na tela caso ocorra alguma execessao ou nao encontre o animal
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }

        return resposta;
    }//final método buscarProfissionalByIdProfissional

    /**
     * Método para incluir um novo profissional
     * @param profissional 
     */
    public void inserirProfissional(Profissional prof) {
        String msg;
        //int idAnimal = buscarIdPetAtual();
        //idAnimal = idAnimal + 1;
       
        //double peso = Double.parseDouble(animal.getPeso());
        //DecimalFormat formato = new DecimalFormat("#.##");      
        //peso = Double.valueOf(formato.format(peso));
        //double altura = Double.parseDouble(animal.getAltura());
        //altura = Double.valueOf(formato.format(altura));
        //System.out.println("Peso = "+ peso+   " altura: "+altura);
        
        msg = "";
        conexao = DBPetFast.getConnection();

        /*Modelo de sql:
        INSERT INTO DAC.ANIMAL (IDANIMAL, IDCLIENTE, NOME, ESPECIE, NASCIMENTO, RACA, PESO, ALTURA, COR, CARACTERISTICA, SEXO) 
	VALUES (1, 1, 'Caçarola', 'Canina', '12/05/2006', 'Viralata', 21.0, 12.0, 'Marrom', 'Pelo caindo', 'M');
        */
        String sql = "INSERT INTO profissional VALUES ("
                + Integer.parseInt(prof.getIdProfissional())+ ", "
                + "'" + prof.getNome() + "', "
                + "'" + prof.getFotoProfissional() + "', "
                + "'" + prof.getCelular() + "', "
                + "'" + prof.getEmail() + "', "
                + "'" + prof.getCpf() + "', "
                + "'" + prof.getRg() + "', "
                + "'" + prof.getNascimento() + "', "
                + "'" + prof.getContato() + "', "
                + "'" + prof.getTelefoneContato()+ "')";
        
        //Preparação do comando sql
        System.out.println(sql);
        try {
            stmt = conexao.createStatement();
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            stmt.executeUpdate(sql);
              msg = msg+"Dados do Profissional inseridos com sucesso \n";
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        try {
            if (conexao.isClosed()){
                // msg = msg+"Conexão ao banco fechada";
                JOptionPane.showMessageDialog(null,msg );
            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            JOptionPane.showMessageDialog(null,msg );
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//Final método inserirProfissional
    
     /**
     * Método para alterar um profissional cadastrado
     * @param profissional
     * @param vid 
     */
    public void alterarProfissional(Profissional prof, String vid){
     String msg;
        msg="";
        conexao = DBPetFast.getConnection();
        try {
            stmt = conexao.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "UPDATE profissional SET "
                + "idProfissional = " + Integer.parseInt(prof.getIdProfissional()) +", "
                + "nome = '" + prof.getNome() + "', "
                + "fotoprofissional = '" + prof.getFotoProfissional() + "', "
                + "celular = '" + prof.getCelular() + "', "
                + "email = '" + prof.getEmail() + "', "
                + "cpf = '" + prof.getCpf() + "', "
                + "rg = '" + prof.getRg() + "', "
                + "nascimento = '" + prof.getNascimento() + "', "
                + "contato = '" + prof.getContato() + "', "
                + "telefonecontato = '" + prof.getTelefoneContato() + "' "
           + " WHERE IDPROFISSIONAL = "+Integer.parseInt(vid);
        System.out.println(sql);
        
        try {
            stmt.executeUpdate(sql);
            msg = msg+"Dados do profissional alterados com sucesso \n";
        } catch (SQLException ex) {
            msg = reduzString(msg+ex);
            msg = reduzString(msg);
            msg = msg+"Erro de gravação no BD \n";
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }

    }//Final método alterarProfissional
    
    /**
     * Método para excluir um profissional do cadastro
     * @param profissional
     * @param vid 
     */
    public void deletarProfissional(String nomeProfissional, int vid){
       String msg;
        msg="";
        
        conexao = DBPetFast.getConnection();
        
        try {
            stmt = conexao.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql ="DELETE FROM profissional WHERE IDPROFISSIONAL = " + vid +" AND NOME = "+"'"+nomeProfissional+"'";
        
        System.out.println(sql);
        int n = JOptionPane.showConfirmDialog(
            null,
            "Confirma Deletar Profissional?",
            "Confirmar Deletar Profissional",
            JOptionPane.YES_NO_OPTION);
           
            if(n == 0){
           try {
               stmt.executeUpdate(sql);
               msg = msg+"Dados do profissional excluidos com sucesso \n";
                } catch (SQLException ex) {
                msg = reduzString(msg+ex);
                 msg = reduzString(msg);
                msg = msg+"Erro de gravação no BD \n";
               Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
           }else{
              msg = msg+"Dados do profissional inalterados \n";  
            }
            
         if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
         
    }//Final método deletarProfissional
    
    
    /**
     * Método para encontrar uma lista de nome de profissionais 
     * @param id
     * @return 
     */
    public ArrayList findComboProfissionalNome(int id) {
        String msg = "";
        String sql = "SELECT nome FROM profissional WHERE idProfissional = "+id+ " ORDER BY 1 ASC";
         System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        //listaAnimal = null;
        ArrayList lista;
        lista = null;
        //preparando a conexao com o banco Petfast
        try {
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //executando o comando sql
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lista = new ArrayList();
        
        try {
            while (rs.next()) {
                lista.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        
         return lista;
    }//fim método findComboProfissionalNome
    
    /**
     * Método que lista profissionais passando como paramentro parte do seu nome.
     * @param nomeAnimal
     * @return 
     */
    public List<Profissional> listarProfissionaisNome(String nomeProfissional) {
       List<Profissional> listaProfissional = new ArrayList<Profissional>();
       String msg = "";
        //modelo sql da consulta: SELECT * FROM cliente WHERE upper(nome) LIKE upper('%mario%')
        String sql = "SELECT * FROM profissional WHERE upper(nome) LIKE upper('" + "%" + nomeProfissional + "%" + "')";
        System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;

        try {
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
               
                Profissional prof = new Profissional();
                
                prof.setIdProfissional(rs.getString("idprofissional"));
                prof.setNome(rs.getString("nome"));
                prof.setFotoProfissional(rs.getString("fotoprofissional"));
                prof.setCelular(rs.getString("celular"));
                prof.setEmail(rs.getString("email"));
                prof.setCpf(rs.getString("cpf"));
                prof.setRg(rs.getString("rg"));
                prof.setNascimento(rs.getString("nascimento"));
                prof.setContato(rs.getString("contato"));
                prof.setTelefoneContato(rs.getString("telefonecontato"));
                //adiciona objeto na lista
                listaProfissional.add(prof);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return listaProfissional;
        
    }// final método listarProfissionalNome()

     /**
     * Método para localizar um profissional passando como paramentro o 
     * id do profissional.
     * @param idProfissional
     * @return objeto profissional 
     */
    public Profissional buscarProfissionalId(int idProfissional) {
        Profissional  prof = new Profissional();

        //variaveis do método
        String msg = "";
        String sql = "SELECT * FROM profissional WHERE idprofissional LIKE '" + idProfissional+"" + "'";
        System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;

        try {
            //preparacao para buscar no banco
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Buscando no banco a instrucao/comando
        try {
            if (rs.first()) {
                //utilizando o objeto animal estanciado no início do método

                prof.setIdProfissional(rs.getString("idprofissional"));
                prof.setNome(rs.getString("nome"));
                prof.setFotoProfissional(rs.getString("fotoprofissional"));
                prof.setCelular(rs.getString("celular"));
                prof.setEmail(rs.getString("email"));
                prof.setCpf(rs.getString("cpf"));
                prof.setRg(rs.getString("rg"));
                prof.setNascimento(rs.getString("nascimento"));
                prof.setContato(rs.getString("contato"));
                prof.setTelefoneContato(rs.getString("telefonecontato"));
               
            } else {
                msg = msg + "Profissional não encontrado \n";
            }
        } catch (SQLException ex) {

            Logger.getLogger(ProfissionalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //envia mensagem na tela caso ocorra alguma execessao ou nao encontre o profissional
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return prof;

    }//final método buscarProfissionalNome
}//final da classe ProfissionalDAO
