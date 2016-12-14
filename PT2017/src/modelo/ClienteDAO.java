/*
 * Este Software tem Objetivo Educacional
 * Para fins de aprendizagem e avaliacao na
 * Na Disciplina de Laboratório Engenharia
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - julho a Dezembro 2016 
 * Aluno Decio Antonio de Carvalho  * 
 */
package modelo;

import static controle.Util.reduzString;
import java.awt.HeadlessException;
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
 * @author deciodecarvalho
 */
public class ClienteDAO {

    private Connection conexao;
    private Statement stmt;
    private ResultSet rs;
    public int idClienteNow = 0;

    public ClienteDAO() {
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
     * Método para atualizar com o idCliente de maior valor ao iniciar o
     * programa
     *
     * @return
     */
    @SuppressWarnings("null")
    public int buscarIdClienteAtual() {
        int resposta = 0;
        String msg;
        msg = "";
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        try {
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery("SELECT * FROM cliente ORDER BY 1 DESC"); //select * from DAC.CLIENTE order BY 1 DESC

        } catch (SQLException ex) {
            msg = "" + ex;
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.first()) {
                idClienteNow = rs.getInt(1);
                resposta = idClienteNow;

                close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            msg = "" + ex;
            JOptionPane.showMessageDialog(null, reduzString(msg));
            close();
            resposta = 0;
        }

        return resposta;
    }//fim buscar idClienteAtual

    /**
     * Método que consulta se o cpf já está cadastrado no sistema Aerofast
     *
     * @param cpf
     * @return boolean resposta
     */
    public boolean buscarExisteClienteCPF(String cpf) {
        boolean resposta = true;
        String msg, sql;
        msg = "";
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        sql = "";
        try {
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery("SELECT * FROM cliente WHERE cpf = '" + cpf + "'");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, reduzString(msg + ex));
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.first()) {
                close();
                resposta = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            msg = "" + ex;
            JOptionPane.showMessageDialog(null, reduzString(msg));
            close();
            resposta = false;
        }

        return resposta;
    }//Fim buscarExisteClienteCPF

    /**
     * Método para capturar dados clientes enviando cpf dele.
     *
     * @param cpf
     * @return Retorna cliente se encontrar CPF no banco Aerofast
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Cliente buscarClienteCPF(String cpf) throws ClassNotFoundException, SQLException {
        Cliente cliente = new Cliente();
        String msg;
        msg = "";
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        //stmt = conexao.createStatement();
        stmt = conexao.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("SELECT * FROM cliente WHERE cpf = '" + cpf + "'");
        if (rs.first()) {
            //cliente.idCliente
            cliente.setIdCliente(rs.getString(1));
            //cliente.nome;
            cliente.setNome(rs.getString(2));
            //cliente.nascimento;
            cliente.setNascimento(rs.getString(3));
            //cliente.endereco;
            cliente.setEndereco(rs.getString(4));
            //clente.Numero;
            cliente.setNumero(rs.getString(5));
            //cliente.Bairro;
            cliente.setBairro(rs.getString(6));
            //cliente.cidade;
            cliente.setCidade(rs.getString(7));
            //cliente.uf;
            cliente.setUf(rs.getString(8));
            //cliente.cep;
            cliente.setCep(rs.getString(9));
            //cliente.email;
            cliente.setEmail(rs.getString(10));
            //cliente.telefone;
            cliente.setTelefone(rs.getString(11));
            //cliente.rg;
            cliente.setRg(rs.getString(12));
            //cliente.cpf;
            cliente.setCpf(rs.getString(13));
            close();

            return cliente;

        } else {
           //msg="Cliente não encontrado";
            //JOptionPane.showMessageDialog(null, msg);
            close();
            return null;
        }

    }// fim buscar cliente CPF

    public Cliente buscarClienteRG(String rg) throws ClassNotFoundException, SQLException {
        Cliente cliente = new Cliente();
        String msg;
        msg = "";
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        //stmt = conexao.createStatement();
        stmt = conexao.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("SELECT * FROM cliente WHERE rg = '" + rg + "'");
        if (rs.first()) {

            cliente.setIdCliente(rs.getString(1));
            cliente.setNome(rs.getString(2));
            cliente.setNascimento(rs.getString(3));
            cliente.setEndereco(rs.getString(4));
            cliente.setNumero(rs.getString(5));
            cliente.setBairro(rs.getString(6));
            cliente.setCidade(rs.getString(7));
            cliente.setUf(rs.getString(8));
            cliente.setCep(rs.getString(9));
            cliente.setEmail(rs.getString(10));
            cliente.setTelefone(rs.getString(11));
            cliente.setRg(rs.getString(12));
            cliente.setCpf(rs.getString(13));
            close();

            return cliente;

        } else {
            msg = "Cliente não encontrado";
            JOptionPane.showMessageDialog(null, msg);
            close();
            return null;
        }

    }// fim buscar cliente

    /**
     * Método para inserir novo Ciente ao Banco de Dados.
     *
     * @param cliente
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void inserirNovoCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        String msg;

        int idCliente = buscarIdClienteAtual();
        //System.out.println(idCliente);
        idCliente = idCliente + 1;

        //System.out.println("\nEste id vai pro banco :"+idCliente);
        msg = "";
        conexao = DBPetFast.getConnection();
        stmt = conexao.createStatement();
        String sql = "INSERT INTO cliente VALUES ("
                //+ parseInt(cliente.getIdCliente()) +", "
                + idCliente + ", "
                + "'" + cliente.getNome() + "', "
                + "'" + cliente.getNascimento() + "', "
                + "'" + cliente.getEndereco() + "', "
                + "'" + cliente.getNumero() + "', "
                + "'" + cliente.getBairro() + "', "
                + "'" + cliente.getCidade() + "', "
                + "'" + cliente.getUf() + "', "
                + "'" + cliente.getCep() + "', "
                + "'" + cliente.getEmail() + "', "
                + "'" + cliente.getTelefone() + "', "
                + "'" + cliente.getRg() + "', "
                + "'" + cliente.getCpf() + "')";

        try {
            stmt.executeUpdate(sql);

            msg = msg + "Dados do cliente inseridos com sucesso \n";
            // JOptionPane.showMessageDialog(null, msg );
        } catch (SQLException | HeadlessException e) {
            msg = reduzString(msg + e);
            msg = msg + "Erro de gravação no BD \n";
            // JOptionPane.showMessageDialog(null,msg );
        }
        close();
        /*
        if (conexao.isClosed()) {
            msg = msg + "Conexão ao banco fechada";
            JOptionPane.showMessageDialog(null, msg);
        }
        */

    }//fim inserir cliente

    /**
     * método para realizar qualquer ateração no cadastro do cliente.
     *
     * @param cliente
     * @param vcpf
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void alterarCliente(Cliente cliente, String vcpf) throws ClassNotFoundException, SQLException {
        String msg;
        msg = "";
        conexao = DBPetFast.getConnection();
        stmt = conexao.createStatement();

        //String sql = "UPDATE cliente SET " + "CPF = '"+ cliente.getCpf()+ "' WHERE CPF = '" + vcpf + "'" ;    
        String sql = "UPDATE cliente SET "
                //+ "idCliente = "+ parseInt(cliente.getIdCliente())+", "
                + "nome = '" + cliente.getNome() + "', "
                + "nascimento = '" + cliente.getNascimento() + "', "
                + "endereco = '" + cliente.getEndereco() + "', "
                + "numero = '" + cliente.getNumero() + "', "
                + "bairro = '" + cliente.getBairro() + "', "
                + "cidade = '" + cliente.getCidade() + "', "
                + "uf = '" + cliente.getUf() + "', "
                + "cep = '" + cliente.getCep() + "', "
                + "email = '" + cliente.getEmail() + "', "
                + "telefone = '" + cliente.getTelefone() + "', "
                + "RG = '" + cliente.getRg() + "', "
                + "CPF = '" + cliente.getCpf() + "' "
                + " WHERE CPF = '" + vcpf + "'";

        try {
            stmt.executeUpdate(sql);

            msg = msg + "Dados do cliente alterados com sucesso \n";
            //JOptionPane.showMessageDialog(null, reduzString(sql) );
        } catch (SQLException | HeadlessException e) {
            msg = reduzString(msg + e);
            msg = reduzString(msg);
            msg = msg + "Erro de gravação no BD \n";
            // JOptionPane.showMessageDialog(null,msg );
        }
        close();
        if (conexao.isClosed()) {
            msg = msg + "Conexão ao banco fechada";
            JOptionPane.showMessageDialog(null, msg);
        }

    }//fim alterar cliente

    /**
     * método para deletar o cliente selecionado após nova confirmação.
     *
     * @param cliente
     * @param cpf
     */
    public void deletarCliente(Cliente cliente, String vcpf) throws SQLException {
        String msg;
        msg = "";

        conexao = DBPetFast.getConnection();
        try {
            stmt = conexao.createStatement();
        } catch (SQLException ex) {
            msg = msg + ex;
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "DELETE FROM cliente WHERE CPF = '" + vcpf + "'";

        try {
            int n = JOptionPane.showConfirmDialog(
                    null,
                    "Confirma Deletar Cliente?",
                    "Confirmar Deletar Cliente",
                    JOptionPane.YES_NO_OPTION);
            if (true) {
                stmt.executeUpdate(sql);
            }

            msg = msg + "Dados do cliente excluidos com sucesso \n";
            // JOptionPane.showMessageDialog(null, msg );
        } catch (SQLException | HeadlessException e) {
            msg = reduzString(msg + e);
            msg = reduzString(msg);
            msg = msg + "Erro de gravação no BD \n";
            // JOptionPane.showMessageDialog(null,msg );
        }
        close();
        /*
        if (conexao.isClosed()) {
            msg = msg + "Conexão ao banco fechada";
            JOptionPane.showMessageDialog(null, msg);
        }
        */

    }//fim deletar cliente

    /**
     * Método para listar cliente para utilizar no relatório
     *
     * @return
     */
    public ArrayList findComboClienteNome() {
        String msg = "";
        String sql = "SELECT nome FROM cliente ORDER BY 1 ASC";
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;

        ArrayList lista;
        lista = null;

        try {
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        lista = new ArrayList();

        try {
            while (rs.next()) {
                lista.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }

        return lista;

    }// fim método findComboClienteNome

    /**
     * Método para listar clientes para o relatórios
     *
     * @return
     */
    public List<Cliente> listarClientes() {

        List<Cliente> listaClientesNome = new ArrayList<>();
        String msg = "";
        String sql = "SELECT * FROM cliente ORDER BY 1 ASC";
        System.out.println(sql);
        conexao = DBPetFast.getConnection();
        ResultSet rs;
        rs = null;
        ArrayList lista; //remover?
        lista = null;

        try {
            stmt = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getString("idcliente"));
                cl.setNome(rs.getString("nome"));
                cl.setNascimento(rs.getString("nascimento"));
                cl.setEndereco(rs.getString("endereco"));
                cl.setNumero(rs.getString("numero"));
                cl.setBairro(rs.getString("bairro"));
                cl.setCidade(rs.getString("cidade"));
                cl.setUf(rs.getString("uf"));
                cl.setCep(rs.getString("cep"));
                cl.setEmail(rs.getString("email"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setRg(rs.getString("rg"));
                cl.setCpf(rs.getString("cpf"));
                listaClientesNome.add(cl);

            }
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return listaClientesNome;
    }//Fim método listarClientes()

    /**
     * Método para listar cliente pelo nome para utilizar nos relatórios
     *
     * @param nomeCliente
     * @return
     */
    public List<Cliente> listarClienteNome(String nomeCliente) {
        List<Cliente> listaCliente = new ArrayList<Cliente>();

        String msg = "";
        //modelo sql da consulta: SELECT * FROM cliente WHERE upper(nome) LIKE upper('%mario%')
        String sql = "SELECT * FROM cliente WHERE upper(nome) LIKE upper('" + "%" + nomeCliente + "%" + "')";
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getString("idcliente"));
                cl.setNome(rs.getString("nome"));
                cl.setNascimento(rs.getString("nascimento"));
                cl.setEndereco(rs.getString("endereco"));
                cl.setNumero(rs.getString("numero"));
                cl.setBairro(rs.getString("bairro"));
                cl.setCidade(rs.getString("cidade"));
                cl.setUf(rs.getString("uf"));
                cl.setCep(rs.getString("cep"));
                cl.setEmail(rs.getString("email"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setRg(rs.getString("rg"));
                cl.setCpf(rs.getString("cpf"));
                listaCliente.add(cl);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return listaCliente;
    }

    /**
     *
     * @param nomeCliente
     * @return cliente
     */
    public Cliente buscarClienteNome(String nomeCliente) {
        Cliente cl = new Cliente();

        String msg = "";
        String sql = "SELECT * FROM cliente WHERE upper(nome) LIKE upper('" + nomeCliente + "')";
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.first()) {
                //Cliente cl = new Cliente();
                cl.setIdCliente(rs.getString("idcliente"));
                cl.setNome(rs.getString("nome"));
                cl.setNascimento(rs.getString("nascimento"));
                cl.setEndereco(rs.getString("endereco"));
                cl.setNumero(rs.getString("numero"));
                cl.setBairro(rs.getString("bairro"));
                cl.setCidade(rs.getString("cidade"));
                cl.setUf(rs.getString("uf"));
                cl.setCep(rs.getString("cep"));
                cl.setEmail(rs.getString("email"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setRg(rs.getString("rg"));
                cl.setCpf(rs.getString("cpf"));
            } else {
                msg = msg + "Cliente não encontrado \n";
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return cl;
    }//Fim método buscarClientesNome()

    /**
     *
     * @param nomeCliente
     * @return cliente
     */
    public Cliente buscarClienteTelefone(String telefoneCliente) {
        Cliente cl = new Cliente();

        String msg = "";
        String sql = "SELECT * FROM cliente WHERE telefone LIKE " + "'" + telefoneCliente + "'";
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.first()) {
                //Cliente cl = new Cliente();
                cl.setIdCliente(rs.getString("idcliente"));
                cl.setNome(rs.getString("nome"));
                cl.setNascimento(rs.getString("nascimento"));
                cl.setEndereco(rs.getString("endereco"));
                cl.setNumero(rs.getString("numero"));
                cl.setBairro(rs.getString("bairro"));
                cl.setCidade(rs.getString("cidade"));
                cl.setUf(rs.getString("uf"));
                cl.setCep(rs.getString("cep"));
                cl.setEmail(rs.getString("email"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setRg(rs.getString("rg"));
                cl.setCpf(rs.getString("cpf"));
            } else {
                //msg = msg + "Cliente não encontrado \n";
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            //    JOptionPane.showMessageDialog(null, msg);
        }
        return cl;
    }//Fim método buscarClienteTelefone()

    public List<Cliente> listarClientePorNome(String nomeCliente) {
        List<Cliente> listaCliente = new ArrayList<Cliente>();

        String msg = "";
        //modelo sql da consulta: SELECT * FROM cliente WHERE upper(nome) LIKE upper('%mario%')
        String sql = "SELECT * FROM cliente WHERE upper(nome) LIKE upper('" + "%" + nomeCliente + "%" + "')";
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getString("idcliente"));
                cl.setNome(rs.getString("nome"));
                cl.setNascimento(rs.getString("nascimento"));
                cl.setEndereco(rs.getString("endereco"));
                cl.setNumero(rs.getString("numero"));
                cl.setBairro(rs.getString("bairro"));
                cl.setCidade(rs.getString("cidade"));
                cl.setUf(rs.getString("uf"));
                cl.setCep(rs.getString("cep"));
                cl.setEmail(rs.getString("email"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setRg(rs.getString("rg"));
                cl.setCpf(rs.getString("cpf"));
                listaCliente.add(cl);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return listaCliente;
    }//Final método listarClientePorNome

    public Cliente buscarClientePorNome(String nomeCliente) {
        Cliente cl = null;
        String msg = "";
        //modelo sql da consulta: SELECT * FROM cliente WHERE upper(nome) LIKE upper('%mario%')
        String sql = "SELECT * FROM cliente WHERE upper(nome) LIKE upper('" + "%" + nomeCliente + "%" + "')";
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.first()) {
                cl = new Cliente();
                cl.setIdCliente(rs.getString("idcliente"));
                cl.setNome(rs.getString("nome"));
                cl.setNascimento(rs.getString("nascimento"));
                cl.setEndereco(rs.getString("endereco"));
                cl.setNumero(rs.getString("numero"));
                cl.setBairro(rs.getString("bairro"));
                cl.setCidade(rs.getString("cidade"));
                cl.setUf(rs.getString("uf"));
                cl.setCep(rs.getString("cep"));
                cl.setEmail(rs.getString("email"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setRg(rs.getString("rg"));
                cl.setCpf(rs.getString("cpf"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }

        return cl;
    }

    public String buscarClientePorId(String idCliente) {
    String cli = "";    
    Cliente cl = new Cliente();

        String msg = "";
        String sql = "SELECT * FROM cliente WHERE idcliente = "+idCliente;
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.first()) {
                //Cliente cl = new Cliente();
                cl.setIdCliente(rs.getString("idcliente"));
                cl.setNome(rs.getString("nome"));
                cl.setNascimento(rs.getString("nascimento"));
                cl.setEndereco(rs.getString("endereco"));
                cl.setNumero(rs.getString("numero"));
                cl.setBairro(rs.getString("bairro"));
                cl.setCidade(rs.getString("cidade"));
                cl.setUf(rs.getString("uf"));
                cl.setCep(rs.getString("cep"));
                cl.setEmail(rs.getString("email"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setRg(rs.getString("rg"));
                cl.setCpf(rs.getString("cpf"));
            } else {
                msg = msg + "Cliente não encontrado \n";
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return cl.getNome();
    
    
    }

    public Cliente buscarClientePorIdInt(int idCliente) {
    String cli = "";    
    Cliente cl = new Cliente();

        String msg = "";
        String sql = "SELECT * FROM cliente WHERE idcliente = "+idCliente;
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            msg = msg + ex + "\n";
            msg = reduzString(msg);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.first()) {
                
                cl.setIdCliente(rs.getString("idcliente"));
                cl.setNome(rs.getString("nome"));
                cl.setNascimento(rs.getString("nascimento"));
                cl.setEndereco(rs.getString("endereco"));
                cl.setNumero(rs.getString("numero"));
                cl.setBairro(rs.getString("bairro"));
                cl.setCidade(rs.getString("cidade"));
                cl.setUf(rs.getString("uf"));
                cl.setCep(rs.getString("cep"));
                cl.setEmail(rs.getString("email"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setRg(rs.getString("rg"));
                cl.setCpf(rs.getString("cpf"));
                
            } else {
                msg = msg + "Cliente não encontrado \n";
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(msg)) {
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
        return cl;
    
    
    }//Final método buscarClientePorIdInt

} // final da classe ClienteDAO
