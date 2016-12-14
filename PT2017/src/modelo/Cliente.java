/*
 * Este Software tem Objetivo Educacional
 * Para fins de TCC
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - Julho a Dezembnro  
 * Aluno Decio Antonio de Carvalho  * 
 */
package modelo;

/**
 * @version 0.1
 * @author Décio Antonio de Carvalho
 * @since 22-maio-2016
 */
public class Cliente {
   private String idCliente;
   private String nome;
   private String nascimento;
   private String endereco;
   private String numero;
   private String bairro;
   private String cidade;
   private String uf;
   private String cep;
   private String email;
   private String telefone;
   private String rg;
   private String cpf;
   public static int vidCliente;
   
    public Cliente() {
    }
    
    /**
     * Método Construtor
     * @param idCliente
     * @param nome
     * @param nascimento
     * @param endereco
     * @param numero
     * @param bairro
     * @param cidade
     * @param uf
     * @param cep
     * @param email
     * @param telefone
     * @param rg
     * @param cpf 
     */
    public Cliente(String idCliente, String nome, String nascimento, String endereco, String numero, String bairro, String cidade, String uf, String cep, String email, String telefone, String rg, String cpf) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.rg = rg;
        this.cpf = cpf;
    }
    
       
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
